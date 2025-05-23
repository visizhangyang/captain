package com.porn.service.recharge.cron;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.vo.BaseVo;
import com.porn.client.config.api.ConfigApiService;
import com.porn.client.config.dto.ConfigQueryDTO;
import com.porn.client.config.vo.ConfigVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.ErcTransferItemDTO;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.dto.RechargeSaveOrUpdateDTO;
import com.porn.client.recharge.dto.TrcTransferItemDTO;
import com.porn.client.recharge.enums.RechargeStatusEnum;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.wallet.api.WalletAddressApiService;
import com.porn.client.wallet.dto.WalletAddressQueryDTO;
import com.porn.client.wallet.dto.WalletAddressSaveOrUpdateDTO;
import com.porn.client.wallet.enums.AddressStatusEnum;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.client.wallet.vo.WalletAddressVo;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.recharge.config.ProxyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Closeable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class RechargeCron
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(RechargeCron.class);

    private static final int EXPIRE_MIN = 30;


    @Autowired
    private WalletAddressApiService walletAddressApiService;


    @Autowired
    private RechargeApiService rechargeApiService;


    @Autowired
    private AccountApiService accountApiService;


    @Autowired
    private ParamsetApiService paramsetApiService;

    @Autowired
    private ProxyConfig proxyConfig;

    @Autowired
    private DingdingMsgSender dingdingMsgSender;

    @Autowired
    private ConfigApiService configApiService;

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }


    @Scheduled(cron = "0/5 * * * * ?")
    public void doCompare() {

        WalletAddressQueryDTO walletAddressQueryDTO = WalletAddressQueryDTO.builder().addressStatus(AddressStatusEnum.PAYING.getStatus()).build();

        List<WalletAddressVo> walletAddressVoList = this.walletAddressApiService.queryWalletAddressList(walletAddressQueryDTO);

        if (ObjectUtil.isEmpty(walletAddressVoList)) {

            log.debug("暂时没有待支付地址.");

            return;

        }

        log.debug("开始准备进行支付查询, 待支付地址[{}].", JSON.toJSONString(walletAddressVoList));


        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().status(RechargeStatusEnum.WAIT_PAY.getStatus()).build();

        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);

        if (ObjectUtil.isEmpty(rechargeVoList)) {

            if (ObjectUtil.isNotEmpty(walletAddressVoList)) {

                for (WalletAddressVo walletAddressVo : walletAddressVoList) {

                    try {

                        ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doExpirePayInfo(walletAddressVo);

                    } catch (Exception e) {

                        log.error("清空锁定信息失败, 异常[{}].", e);

                    }

                }

            }

            return;

        }

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        Map<Long, WalletAddressVo> walletAddressVoMap = (Map<Long, WalletAddressVo>) walletAddressVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));

        for (RechargeVo rechargeVo : rechargeVoList) {

            WalletAddressVo walletAddressVo = walletAddressVoMap.get(rechargeVo.getWalletId());

            try {

                if (isExpire(rechargeVo, paramsetVo)) {

                    ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doExpirePayInfo(rechargeVo, walletAddressVo);
                    continue;

                }

                doProxyQueryPayInfo(rechargeVo, walletAddressVo, paramsetVo);

            } catch (Exception e) {

                log.error("查询支付信息异常, rechargeVo[{}], walletAddressVo[{}], 异常[{}].", new Object[]{JSON.toJSONString((rechargeVo == null) ? "" : rechargeVo), JSON.toJSONString((walletAddressVo == null) ? "" : walletAddressVo), e});

            }

        }

    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doExpirePayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo) {

        RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = ((RechargeSaveOrUpdateDTO.RechargeSaveOrUpdateDTOBuilder) RechargeSaveOrUpdateDTO.builder().id(rechargeVo.getId())).status(RechargeStatusEnum.PAY_TIMEOUT.getStatus()).build();

        this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);

        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = ((WalletAddressSaveOrUpdateDTO.WalletAddressSaveOrUpdateDTOBuilder) WalletAddressSaveOrUpdateDTO.builder().id(rechargeVo.getWalletId())).addressStatus(AddressStatusEnum.NORMAL.getStatus()).clearLockTime(true).build();

        this.walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doExpirePayInfo(WalletAddressVo walletAddressVo) {

        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = ((WalletAddressSaveOrUpdateDTO.WalletAddressSaveOrUpdateDTOBuilder) WalletAddressSaveOrUpdateDTO.builder().id(walletAddressVo.getId())).addressStatus(AddressStatusEnum.NORMAL.getStatus()).clearLockTime(true).build();

        this.walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

    }


    protected boolean isExpire(RechargeVo rechargeVo, ParamsetVo paramsetVo) {

        return

                (LocalDateTimeUtil.between(rechargeVo.getCreateTime(), LocalDateTimeUtil.now()).toMinutes() > (ObjectUtil.isEmpty(paramsetVo) ? 30L : ((Integer) ObjectUtil.defaultIfNull(paramsetVo.getRechargeMatchTime(), Integer.valueOf(30))).intValue()));

    }

    protected void doProxyQueryPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {

        if (WalletChainEnum.TRON.getCode().equals(rechargeVo.getWalletCode())) {

            doQueryTrcPayInfo(rechargeVo, walletAddressVo, paramsetVo);

        } else if (WalletChainEnum.ETH.getCode().equals(rechargeVo.getWalletCode())) {

            doQueryErcPayInfo(rechargeVo, walletAddressVo, paramsetVo);

        } else if (WalletChainEnum.BEP.getCode().equals(rechargeVo.getWalletCode())) {

            doQueryBrcPayInfo(rechargeVo, walletAddressVo, paramsetVo);

        } else if (WalletChainEnum.SOLANA.getCode().equals(rechargeVo.getWalletCode())) {

            doQuerySolPayInfo(rechargeVo, walletAddressVo, paramsetVo);

        }

    }

    protected void doQuerySolPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {
    }


    protected void doQueryBrcPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {

        if (ObjectUtil.isEmpty(rechargeVo) ||
                ObjectUtil.isEmpty(walletAddressVo)) {

            return;

        }


        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("bscscan_appkey").accountId(CommonConst.LZERO).build();

        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);


        HttpResponse httpResponse = null;

        String rspJson = "";

        Map<String, Object> params = MapUtil.newHashMap();

        params.put("module", "account");

        params.put("action", "tokentx");

        params.put("page", "1");

        params.put("offset", "20");

        params.put("sort", "desc");

        params.put("address", rechargeVo.getWalletAddress());

        params.put("contractaddress", "0x55d398326f99059fF775485246999027B3197955");

        params.put("apikey", configVo.getConfigValue());

        try {

            String url = "https://api.bscscan.com/api";

            HttpRequest httpRequest = HttpRequest.get(url).setReadTimeout(5000).setConnectionTimeout(5000).form(params);

            httpResponse = httpRequest.execute();

            rspJson = httpResponse.body();

        } finally {

            IoUtil.close((Closeable) httpResponse);

        }

        if (!httpResponse.isOk() || ObjectUtil.isEmpty(rspJson)) {

            log.error("查询交易数据, 请求入参[{}], 返回结果不正确或为空.", JSON.toJSONString(params));

            return;

        }

        JSONObject jObj = JSON.parseObject(rspJson);

        if (!jObj.containsKey("status") ||
                !Integer.valueOf(1).equals(Integer.valueOf(jObj.getIntValue("status")))) {

            log.error("查询交易数据, 请求入参[{}], 返回结果不正确[{}].", JSON.toJSONString(params), rspJson);

            return;

        }

        JSONArray jDataArr = jObj.getJSONArray("result");

        List<ErcTransferItemDTO> transferItemDTOList = jDataArr.toJavaList(ErcTransferItemDTO.class);

        if (ObjectUtil.isEmpty(transferItemDTOList)) {

            log.error("查询交易数据, 结果为空[{}].", rspJson);

            return;

        }

        Map<String, ErcTransferItemDTO> hashMap = (Map<String, ErcTransferItemDTO>) transferItemDTOList.stream().collect(Collectors.toMap(ErcTransferItemDTO::getHash, Function.identity()));

        List<String> hashList = (List<String>) transferItemDTOList.stream().map(ErcTransferItemDTO::getHash).distinct().collect(Collectors.toList());


        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().hashList(hashList).build();

        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);

        List<String> existsHashList = ListUtil.toList((String[]) new String[0]);

        if (ObjectUtil.isNotEmpty(rechargeVoList)) {

            List<String> dbExistsHashList = (List<String>) rechargeVoList.stream().map(RechargeVo::getHash).distinct().collect(Collectors.toList());

            if (ObjectUtil.isNotEmpty(dbExistsHashList)) {

                existsHashList = dbExistsHashList;

            }

        }

        for (ErcTransferItemDTO ercTransferItemDTO : transferItemDTOList) {

            if (existsHashList.contains(ercTransferItemDTO.getHash())) {

                continue;

            }

            if (rechargeVo.getAmount().compareTo(NumberUtil.div(ercTransferItemDTO.getValue(), NumberUtil.toBigDecimal(StrUtil.padAfter("1", ercTransferItemDTO.getTokenDecimal().intValue() + 1, "0")))) != 0) {

                continue;

            }

            ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doReceiveUsdt(rechargeVo, ercTransferItemDTO.getHash(), ercTransferItemDTO
                    .getFrom(), paramsetVo);

            existsHashList.add(ercTransferItemDTO.getHash());

        }

    }


    protected void doQueryErcPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {

        if (ObjectUtil.isEmpty(rechargeVo) ||
                ObjectUtil.isEmpty(walletAddressVo)) {

            return;

        }

        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("etherscan_appkey").accountId(CommonConst.LZERO).build();

        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);


        HttpResponse httpResponse = null;

        String rspJson = "";

        Map<String, Object> params = MapUtil.newHashMap();

        params.put("module", "account");

        params.put("action", "tokentx");

        params.put("page", "1");

        params.put("offset", "20");

        params.put("sort", "desc");

        params.put("address", rechargeVo.getWalletAddress());

        params.put("contractaddress", "0xdAC17F958D2ee523a2206206994597C13D831ec7");

        params.put("apikey", ObjectUtil.isEmpty(configVo) ? "4DD3KTHG9N282SXSJAQDQS8QYRXMN9WXHZ" : configVo.getConfigValue());

        try {

            String url = "https://api.etherscan.io/api";

            HttpRequest httpRequest = HttpRequest.get(url).setReadTimeout(5000).setConnectionTimeout(5000).form(params);

            httpResponse = httpRequest.execute();

            rspJson = httpResponse.body();

        } finally {

            IoUtil.close((Closeable) httpResponse);

        }

        if (!httpResponse.isOk() || ObjectUtil.isEmpty(rspJson)) {

            log.error("查询交易数据, 请求入参[{}], 返回结果不正确或为空.", JSON.toJSONString(params));

            return;

        }

        JSONObject jObj = JSON.parseObject(rspJson);

        if (!jObj.containsKey("status") ||
                !Integer.valueOf(1).equals(Integer.valueOf(jObj.getIntValue("status")))) {

            log.error("查询交易数据, 请求入参[{}], 返回结果不正确[{}].", JSON.toJSONString(params), rspJson);

            return;

        }

        JSONArray jDataArr = jObj.getJSONArray("result");

        List<ErcTransferItemDTO> transferItemDTOList = jDataArr.toJavaList(ErcTransferItemDTO.class);

        if (ObjectUtil.isEmpty(transferItemDTOList)) {

            log.error("查询交易数据, 结果为空[{}].", rspJson);

            return;

        }

        Map<String, ErcTransferItemDTO> hashMap = (Map<String, ErcTransferItemDTO>) transferItemDTOList.stream().collect(Collectors.toMap(ErcTransferItemDTO::getHash, Function.identity()));

        List<String> hashList = (List<String>) transferItemDTOList.stream().map(ErcTransferItemDTO::getHash).distinct().collect(Collectors.toList());


        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().hashList(hashList).build();

        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);

        List<String> existsHashList = ListUtil.toList((String[]) new String[0]);

        if (ObjectUtil.isNotEmpty(rechargeVoList)) {

            List<String> dbExistsHashList = (List<String>) rechargeVoList.stream().map(RechargeVo::getHash).distinct().collect(Collectors.toList());

            if (ObjectUtil.isNotEmpty(dbExistsHashList)) {

                existsHashList = dbExistsHashList;

            }

        }

        for (ErcTransferItemDTO ercTransferItemDTO : transferItemDTOList) {

            if (existsHashList.contains(ercTransferItemDTO.getHash())) {

                continue;

            }

            if (rechargeVo.getAmount().compareTo(NumberUtil.div(ercTransferItemDTO.getValue(), NumberUtil.toBigDecimal(StrUtil.padAfter("1", ercTransferItemDTO.getTokenDecimal().intValue() + 1, "0")))) != 0) {

                continue;

            }

            ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doReceiveUsdt(rechargeVo, ercTransferItemDTO.getHash(), ercTransferItemDTO
                    .getFrom(), paramsetVo);

            existsHashList.add(ercTransferItemDTO.getHash());

        }

    }

    protected void doQueryTrcPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {

        if (ObjectUtil.isEmpty(rechargeVo) ||
                ObjectUtil.isEmpty(walletAddressVo)) {

            return;

        }

        LocalDateTime start = LocalDateTimeUtil.offset(rechargeVo.getCreateTime(), -240L, ChronoUnit.MINUTES);

        LocalDateTime end = LocalDateTimeUtil.offset(rechargeVo.getCreateTime(), 25L, ChronoUnit.MINUTES);

        Map<String, Object> params = MapUtil.newHashMap();

        params.put("sort", "-timestamp");

        params.put("start", "0");

        params.put("direction", "2");

        params.put("db_version", "1");

        params.put("trc20Id", "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t");

        params.put("address", rechargeVo.getWalletAddress());

        params.put("start_timestamp", Long.valueOf(start.toInstant(ZoneOffset.of("+8")).toEpochMilli()));

        params.put("end_timestamp", Long.valueOf(end.toInstant(ZoneOffset.of("+8")).toEpochMilli()));

        HttpResponse httpResponse = null;

        String rspJson = "";


        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("trc_proxy_config").configGroup("trc_proxy_config_group").accountId(CommonConst.LZERO).build();

        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);

        String url = (ObjectUtil.isEmpty(configVo) || ObjectUtil.isEmpty(configVo.getConfigValue())) ? "https://apilist.tronscanapi.com/api/transfer/trc20" : configVo.getConfigValue().trim();

        log.debug("查询交易数据, 请求的url[{}].", url);

        try {

            HttpRequest httpRequest = (HttpRequest) ((HttpRequest) ((HttpRequest) HttpRequest.get(url).setReadTimeout(5000).setConnectionTimeout(5000).form("sort", "-timestamp").form("start", "0").form("direction", "2").form("db_version", "1").form("trc20Id", "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t").form("address", rechargeVo.getWalletAddress()).form("start_timestamp", Long.valueOf(start.toInstant(ZoneOffset.of("+8")).toEpochMilli())).form("end_timestamp", Long.valueOf(end.toInstant(ZoneOffset.of("+8")).toEpochMilli())).header("Origin", "https://tronscan.org")).header("Referer", "https://tronscan.org/")).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

            if (ObjectUtil.isNotEmpty(this.proxyConfig.getHost()) &&
                    ObjectUtil.isNotEmpty(this.proxyConfig.getPort())) {

                httpRequest.setHttpProxy(this.proxyConfig.getHost(), this.proxyConfig.getPort().intValue());

            }

            httpResponse = httpRequest.execute();

            rspJson = httpResponse.body();

        } finally {

            IoUtil.close((Closeable) httpResponse);

        }

        if (!httpResponse.isOk() || ObjectUtil.isEmpty(rspJson)) {

            log.debug("查询交易数据, 请求入参[{}], http[{}], 结果[{}], 返回结果不正确或为空.", new Object[]{JSON.toJSONString(params), Boolean.valueOf(httpResponse.isOk()), rspJson});

            return;

        }

        JSONObject jObj = JSON.parseObject(rspJson);

        if (!jObj.containsKey("code") ||
                !Integer.valueOf(200).equals(Integer.valueOf(jObj.getIntValue("code")))) {

            log.debug("查询交易数据, 请求入参[{}], 返回结果不正确[{}].", JSON.toJSONString(params), rspJson);

            return;

        }

        JSONArray jDataArr = jObj.getJSONArray("data");

        List<TrcTransferItemDTO> transferItemDTOList = jDataArr.toJavaList(TrcTransferItemDTO.class);

        if (ObjectUtil.isEmpty(transferItemDTOList)) {

            log.debug("查询交易数据, 结果为空[{}].", rspJson);

            return;

        }

        transferItemDTOList = (List<TrcTransferItemDTO>) transferItemDTOList.stream().filter(ts -> "SUCCESS".equalsIgnoreCase(ts.getContract_ret())).collect(Collectors.toList());

        if (ObjectUtil.isEmpty(transferItemDTOList)) {

            log.debug("查询交易数据, 结果成功部分为空[{}].", rspJson);

            return;

        }

        Map<String, TrcTransferItemDTO> hashMap = (Map<String, TrcTransferItemDTO>) transferItemDTOList.stream().collect(Collectors.toMap(TrcTransferItemDTO::getHash, Function.identity()));

        List<String> hashList = (List<String>) transferItemDTOList.stream().map(TrcTransferItemDTO::getHash).distinct().collect(Collectors.toList());


        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().hashList(hashList).build();

        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);

        List<String> existsHashList = ListUtil.toList((String[]) new String[0]);

        if (ObjectUtil.isNotEmpty(rechargeVoList)) {

            List<String> dbExistsHashList = (List<String>) rechargeVoList.stream().map(RechargeVo::getHash).distinct().collect(Collectors.toList());

            if (ObjectUtil.isNotEmpty(dbExistsHashList)) {

                existsHashList = dbExistsHashList;

            }

        }

        for (TrcTransferItemDTO trcTransferItemDTO : transferItemDTOList) {

            if (existsHashList.contains(trcTransferItemDTO.getHash())) {

                continue;

            }

            if (rechargeVo.getAmount().compareTo(NumberUtil.div(trcTransferItemDTO.getAmount(), NumberUtil.toBigDecimal(StrUtil.padAfter("1", trcTransferItemDTO.getDecimals().intValue() + 1, "0")))) != 0) {

                continue;

            }

            ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doReceiveUsdt(rechargeVo, trcTransferItemDTO.getHash(), trcTransferItemDTO
                    .getFrom(), paramsetVo);

            existsHashList.add(trcTransferItemDTO.getHash());

        }

    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doReceiveUsdt(RechargeVo rechargeVo, String hash, String from, ParamsetVo paramsetVo) {

        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(rechargeVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).operateAmount(rechargeVo.getReceiveAmount()).bizId(rechargeVo.getId()).streamTypeEnum(StreamTypeEnum.RECHARGE).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = ((RechargeSaveOrUpdateDTO.RechargeSaveOrUpdateDTOBuilder) RechargeSaveOrUpdateDTO.builder().id(rechargeVo.getId())).hash(hash).fromAddress(from).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).build();

        this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);

        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = ((WalletAddressSaveOrUpdateDTO.WalletAddressSaveOrUpdateDTOBuilder) WalletAddressSaveOrUpdateDTO.builder().id(rechargeVo.getWalletId())).addressStatus(AddressStatusEnum.NORMAL.getStatus()).clearLockTime(true).build();

        this.walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);


        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(rechargeVo.getAccountId())
                        .msg("账户[" + rechargeVo.getAccountName() + "], 充值[" + rechargeVo.getAmount().stripTrailingZeros().toPlainString() + "]成功.")
                        .build());

    }

}

