
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
    /*  65 */   private static final Logger log = LoggerFactory.getLogger(RechargeCron.class);


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
        /*  99 */
        this.applicationContext = applicationContext;

    }









    @Scheduled(cron = "0/5 * * * * ?")
     public void doCompare() {
        /* 110 */
        WalletAddressQueryDTO walletAddressQueryDTO = WalletAddressQueryDTO.builder().addressStatus(AddressStatusEnum.PAYING.getStatus()).build();
        /* 111 */
        List<WalletAddressVo> walletAddressVoList = this.walletAddressApiService.queryWalletAddressList(walletAddressQueryDTO);
        /* 112 */
        if (ObjectUtil.isEmpty(walletAddressVoList)) {
            /* 113 */
            log.debug("暂时没有待支付地址.");

            return;

        }
        /* 116 */
        log.debug("开始准备进行支付查询, 待支付地址[{}].", JSON.toJSONString(walletAddressVoList));




        /* 121 */
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().status(RechargeStatusEnum.WAIT_PAY.getStatus()).build();
        /* 122 */
        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);
        /* 123 */
        if (ObjectUtil.isEmpty(rechargeVoList)) {
            /* 124 */
            if (ObjectUtil.isNotEmpty(walletAddressVoList))
                 {
                /* 126 */
                for (WalletAddressVo walletAddressVo : walletAddressVoList) {


                    try {
                        /* 129 */
                        ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doExpirePayInfo(walletAddressVo);
                        /* 130 */
                    } catch (Exception e) {
                        /* 131 */
                        log.error("清空锁定信息失败, 异常[{}].", e);

                    }

                }

            }


            return;

        }

        /* 139 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        /* 142 */
        Map<Long, WalletAddressVo> walletAddressVoMap = (Map<Long, WalletAddressVo>) walletAddressVoList.stream().collect(Collectors.toMap(BaseVo::getId, Function.identity()));
        /* 143 */
        for (RechargeVo rechargeVo : rechargeVoList) {
            /* 144 */
            WalletAddressVo walletAddressVo = walletAddressVoMap.get(rechargeVo.getWalletId());


            try {
                /* 147 */
                if (isExpire(rechargeVo, paramsetVo)) {

                    /* 149 */
                    ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doExpirePayInfo(rechargeVo, walletAddressVo);
                    continue;

                }
                /* 151 */
                doProxyQueryPayInfo(rechargeVo, walletAddressVo, paramsetVo);

            }
            /* 153 */ catch (Exception e) {
                /* 154 */
                log.error("查询支付信息异常, rechargeVo[{}], walletAddressVo[{}], 异常[{}].", new Object[]{JSON.toJSONString((rechargeVo == null) ? "" : rechargeVo), JSON.toJSONString((walletAddressVo == null) ? "" : walletAddressVo), e});

            }

        }

    }












    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doExpirePayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo) {
        /* 170 */
        RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = ((RechargeSaveOrUpdateDTO.RechargeSaveOrUpdateDTOBuilder) RechargeSaveOrUpdateDTO.builder().id(rechargeVo.getId())).status(RechargeStatusEnum.PAY_TIMEOUT.getStatus()).build();
        /* 171 */
        this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);





        /* 177 */
        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = ((WalletAddressSaveOrUpdateDTO.WalletAddressSaveOrUpdateDTOBuilder) WalletAddressSaveOrUpdateDTO.builder().id(rechargeVo.getWalletId())).addressStatus(AddressStatusEnum.NORMAL.getStatus()).clearLockTime(true).build();
        /* 178 */
        this.walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

    }












    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doExpirePayInfo(WalletAddressVo walletAddressVo) {
        /* 192 */
        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = ((WalletAddressSaveOrUpdateDTO.WalletAddressSaveOrUpdateDTOBuilder) WalletAddressSaveOrUpdateDTO.builder().id(walletAddressVo.getId())).addressStatus(AddressStatusEnum.NORMAL.getStatus()).clearLockTime(true).build();
        /* 193 */
        this.walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);

    }









    protected boolean isExpire(RechargeVo rechargeVo, ParamsetVo paramsetVo) {
        /* 203 */
        return

                /* 205 */       (LocalDateTimeUtil.between(rechargeVo.getCreateTime(), LocalDateTimeUtil.now()).toMinutes() > (ObjectUtil.isEmpty(paramsetVo) ? 30L : ((Integer) ObjectUtil.defaultIfNull(paramsetVo.getRechargeMatchTime(), Integer.valueOf(30))).intValue()));

    }










    protected void doProxyQueryPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {
        /* 216 */
        if (WalletChainEnum.TRON.getCode().equals(rechargeVo.getWalletCode())) {
            /* 217 */
            doQueryTrcPayInfo(rechargeVo, walletAddressVo, paramsetVo);
            /* 218 */
        } else if (WalletChainEnum.ETH.getCode().equals(rechargeVo.getWalletCode())) {
            /* 219 */
            doQueryErcPayInfo(rechargeVo, walletAddressVo, paramsetVo);
            /* 220 */
        } else if (WalletChainEnum.BEP.getCode().equals(rechargeVo.getWalletCode())) {
            /* 221 */
            doQueryBrcPayInfo(rechargeVo, walletAddressVo, paramsetVo);
            /* 222 */
        } else if (WalletChainEnum.SOLANA.getCode().equals(rechargeVo.getWalletCode())) {
            /* 223 */
            doQuerySolPayInfo(rechargeVo, walletAddressVo, paramsetVo);

        }

    }












    protected void doQuerySolPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {
    }











    protected void doQueryBrcPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {
        /* 247 */
        if (ObjectUtil.isEmpty(rechargeVo) ||
                /* 248 */       ObjectUtil.isEmpty(walletAddressVo)) {

            return;

        }




        /* 255 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("bscscan_appkey").accountId(CommonConst.LZERO).build();
        /* 256 */
        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);


        /* 259 */
        HttpResponse httpResponse = null;
        /* 260 */
        String rspJson = "";
        /* 261 */
        Map<String, Object> params = MapUtil.newHashMap();
        /* 262 */
        params.put("module", "account");
        /* 263 */
        params.put("action", "tokentx");
        /* 264 */
        params.put("page", "1");
        /* 265 */
        params.put("offset", "20");
        /* 266 */
        params.put("sort", "desc");
        /* 267 */
        params.put("address", rechargeVo.getWalletAddress());
        /* 268 */
        params.put("contractaddress", "0x55d398326f99059fF775485246999027B3197955");
        /* 269 */
        params.put("apikey", configVo.getConfigValue());


        try {
            /* 272 */
            String url = "https://api.bscscan.com/api";



            /* 276 */
            HttpRequest httpRequest = HttpRequest.get(url).setReadTimeout(5000).setConnectionTimeout(5000).form(params);
            /* 277 */
            httpResponse = httpRequest.execute();
            /* 278 */
            rspJson = httpResponse.body();

        } finally {
            /* 280 */
            IoUtil.close((Closeable) httpResponse);

        }

        /* 283 */
        if (!httpResponse.isOk() || ObjectUtil.isEmpty(rspJson)) {
            /* 284 */
            log.error("查询交易数据, 请求入参[{}], 返回结果不正确或为空.", JSON.toJSONString(params));


            return;

        }
        /* 288 */
        JSONObject jObj = JSON.parseObject(rspJson);
        /* 289 */
        if (!jObj.containsKey("status") ||
                /* 290 */       !Integer.valueOf(1).equals(Integer.valueOf(jObj.getIntValue("status")))) {
            /* 291 */
            log.error("查询交易数据, 请求入参[{}], 返回结果不正确[{}].", JSON.toJSONString(params), rspJson);

            return;

        }
        /* 294 */
        JSONArray jDataArr = jObj.getJSONArray("result");
        /* 295 */
        List<ErcTransferItemDTO> transferItemDTOList = jDataArr.toJavaList(ErcTransferItemDTO.class);
        /* 296 */
        if (ObjectUtil.isEmpty(transferItemDTOList)) {
            /* 297 */
            log.error("查询交易数据, 结果为空[{}].", rspJson);


            return;

        }
        /* 301 */
        Map<String, ErcTransferItemDTO> hashMap = (Map<String, ErcTransferItemDTO>) transferItemDTOList.stream().collect(Collectors.toMap(ErcTransferItemDTO::getHash, Function.identity()));
        /* 302 */
        List<String> hashList = (List<String>) transferItemDTOList.stream().map(ErcTransferItemDTO::getHash).distinct().collect(Collectors.toList());


        /* 305 */
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().hashList(hashList).build();
        /* 306 */
        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);
        /* 307 */
        List<String> existsHashList = ListUtil.toList((String[]) new String[0]);
        /* 308 */
        if (ObjectUtil.isNotEmpty(rechargeVoList)) {

            /* 310 */
            List<String> dbExistsHashList = (List<String>) rechargeVoList.stream().map(RechargeVo::getHash).distinct().collect(Collectors.toList());
            /* 311 */
            if (ObjectUtil.isNotEmpty(dbExistsHashList)) {
                /* 312 */
                existsHashList = dbExistsHashList;

            }

        }

        /* 316 */
        for (ErcTransferItemDTO ercTransferItemDTO : transferItemDTOList) {
            /* 317 */
            if (existsHashList.contains(ercTransferItemDTO.getHash())) {

                continue;

            }

            /* 321 */
            if (rechargeVo.getAmount().compareTo(NumberUtil.div(ercTransferItemDTO.getValue(), NumberUtil.toBigDecimal(StrUtil.padAfter("1", ercTransferItemDTO.getTokenDecimal().intValue() + 1, "0")))) != 0) {

                continue;

            }

            /* 325 */
            ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doReceiveUsdt(rechargeVo, ercTransferItemDTO.getHash(), ercTransferItemDTO
/* 326 */.getFrom(), paramsetVo);
            /* 327 */
            existsHashList.add(ercTransferItemDTO.getHash());

        }

    }









    protected void doQueryErcPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {
        /* 338 */
        if (ObjectUtil.isEmpty(rechargeVo) ||
                /* 339 */       ObjectUtil.isEmpty(walletAddressVo)) {

            return;

        }





        /* 347 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("etherscan_appkey").accountId(CommonConst.LZERO).build();
        /* 348 */
        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);


        /* 351 */
        HttpResponse httpResponse = null;
        /* 352 */
        String rspJson = "";
        /* 353 */
        Map<String, Object> params = MapUtil.newHashMap();
        /* 354 */
        params.put("module", "account");
        /* 355 */
        params.put("action", "tokentx");
        /* 356 */
        params.put("page", "1");
        /* 357 */
        params.put("offset", "20");
        /* 358 */
        params.put("sort", "desc");
        /* 359 */
        params.put("address", rechargeVo.getWalletAddress());
        /* 360 */
        params.put("contractaddress", "0xdAC17F958D2ee523a2206206994597C13D831ec7");
        /* 361 */
        params.put("apikey", ObjectUtil.isEmpty(configVo) ? "4DD3KTHG9N282SXSJAQDQS8QYRXMN9WXHZ" : configVo.getConfigValue());


        try {
            /* 364 */
            String url = "https://api.etherscan.io/api";



            /* 368 */
            HttpRequest httpRequest = HttpRequest.get(url).setReadTimeout(5000).setConnectionTimeout(5000).form(params);
            /* 369 */
            httpResponse = httpRequest.execute();
            /* 370 */
            rspJson = httpResponse.body();

        } finally {
            /* 372 */
            IoUtil.close((Closeable) httpResponse);

        }

        /* 375 */
        if (!httpResponse.isOk() || ObjectUtil.isEmpty(rspJson)) {
            /* 376 */
            log.error("查询交易数据, 请求入参[{}], 返回结果不正确或为空.", JSON.toJSONString(params));


            return;

        }
        /* 380 */
        JSONObject jObj = JSON.parseObject(rspJson);
        /* 381 */
        if (!jObj.containsKey("status") ||
                /* 382 */       !Integer.valueOf(1).equals(Integer.valueOf(jObj.getIntValue("status")))) {
            /* 383 */
            log.error("查询交易数据, 请求入参[{}], 返回结果不正确[{}].", JSON.toJSONString(params), rspJson);

            return;

        }
        /* 386 */
        JSONArray jDataArr = jObj.getJSONArray("result");
        /* 387 */
        List<ErcTransferItemDTO> transferItemDTOList = jDataArr.toJavaList(ErcTransferItemDTO.class);
        /* 388 */
        if (ObjectUtil.isEmpty(transferItemDTOList)) {
            /* 389 */
            log.error("查询交易数据, 结果为空[{}].", rspJson);


            return;

        }
        /* 393 */
        Map<String, ErcTransferItemDTO> hashMap = (Map<String, ErcTransferItemDTO>) transferItemDTOList.stream().collect(Collectors.toMap(ErcTransferItemDTO::getHash, Function.identity()));
        /* 394 */
        List<String> hashList = (List<String>) transferItemDTOList.stream().map(ErcTransferItemDTO::getHash).distinct().collect(Collectors.toList());


        /* 397 */
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().hashList(hashList).build();
        /* 398 */
        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);
        /* 399 */
        List<String> existsHashList = ListUtil.toList((String[]) new String[0]);
        /* 400 */
        if (ObjectUtil.isNotEmpty(rechargeVoList)) {

            /* 402 */
            List<String> dbExistsHashList = (List<String>) rechargeVoList.stream().map(RechargeVo::getHash).distinct().collect(Collectors.toList());
            /* 403 */
            if (ObjectUtil.isNotEmpty(dbExistsHashList)) {
                /* 404 */
                existsHashList = dbExistsHashList;

            }

        }

        /* 408 */
        for (ErcTransferItemDTO ercTransferItemDTO : transferItemDTOList) {
            /* 409 */
            if (existsHashList.contains(ercTransferItemDTO.getHash())) {

                continue;

            }

            /* 413 */
            if (rechargeVo.getAmount().compareTo(NumberUtil.div(ercTransferItemDTO.getValue(), NumberUtil.toBigDecimal(StrUtil.padAfter("1", ercTransferItemDTO.getTokenDecimal().intValue() + 1, "0")))) != 0) {

                continue;

            }

            /* 417 */
            ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doReceiveUsdt(rechargeVo, ercTransferItemDTO.getHash(), ercTransferItemDTO
/* 418 */.getFrom(), paramsetVo);
            /* 419 */
            existsHashList.add(ercTransferItemDTO.getHash());

        }

    }








    protected void doQueryTrcPayInfo(RechargeVo rechargeVo, WalletAddressVo walletAddressVo, ParamsetVo paramsetVo) {
        /* 429 */
        if (ObjectUtil.isEmpty(rechargeVo) ||
                /* 430 */       ObjectUtil.isEmpty(walletAddressVo)) {

            return;

        }

        /* 434 */
        LocalDateTime start = LocalDateTimeUtil.offset(rechargeVo.getCreateTime(), -240L, ChronoUnit.MINUTES);
        /* 435 */
        LocalDateTime end = LocalDateTimeUtil.offset(rechargeVo.getCreateTime(), 25L, ChronoUnit.MINUTES);
        /* 436 */
        Map<String, Object> params = MapUtil.newHashMap();
        /* 437 */
        params.put("sort", "-timestamp");
        /* 438 */
        params.put("start", "0");
        /* 439 */
        params.put("direction", "2");
        /* 440 */
        params.put("db_version", "1");
        /* 441 */
        params.put("trc20Id", "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t");
        /* 442 */
        params.put("address", rechargeVo.getWalletAddress());
        /* 443 */
        params.put("start_timestamp", Long.valueOf(start.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        /* 444 */
        params.put("end_timestamp", Long.valueOf(end.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
        /* 445 */
        HttpResponse httpResponse = null;
        /* 446 */
        String rspJson = "";






        /* 453 */
        ConfigQueryDTO configQueryDTO = ConfigQueryDTO.builder().configCode("trc_proxy_config").configGroup("trc_proxy_config_group").accountId(CommonConst.LZERO).build();
        /* 454 */
        ConfigVo configVo = this.configApiService.queryConfig(configQueryDTO);
        /* 455 */
        String url = (ObjectUtil.isEmpty(configVo) || ObjectUtil.isEmpty(configVo.getConfigValue())) ? "https://apilist.tronscanapi.com/api/transfer/trc20" : configVo.getConfigValue().trim();
        /* 456 */
        log.debug("查询交易数据, 请求的url[{}].", url);
















        try {
            /* 473 */
            HttpRequest httpRequest = (HttpRequest) ((HttpRequest) ((HttpRequest) HttpRequest.get(url).setReadTimeout(5000).setConnectionTimeout(5000).form("sort", "-timestamp").form("start", "0").form("direction", "2").form("db_version", "1").form("trc20Id", "TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t").form("address", rechargeVo.getWalletAddress()).form("start_timestamp", Long.valueOf(start.toInstant(ZoneOffset.of("+8")).toEpochMilli())).form("end_timestamp", Long.valueOf(end.toInstant(ZoneOffset.of("+8")).toEpochMilli())).header("Origin", "https://tronscan.org")).header("Referer", "https://tronscan.org/")).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");

            /* 475 */
            if (ObjectUtil.isNotEmpty(this.proxyConfig.getHost()) &&
                    /* 476 */         ObjectUtil.isNotEmpty(this.proxyConfig.getPort())) {
                /* 477 */
                httpRequest.setHttpProxy(this.proxyConfig.getHost(), this.proxyConfig.getPort().intValue());

            }
            /* 479 */
            httpResponse = httpRequest.execute();
            /* 480 */
            rspJson = httpResponse.body();

        } finally {
            /* 482 */
            IoUtil.close((Closeable) httpResponse);

        }

        /* 485 */
        if (!httpResponse.isOk() || ObjectUtil.isEmpty(rspJson)) {
            /* 486 */
            log.debug("查询交易数据, 请求入参[{}], http[{}], 结果[{}], 返回结果不正确或为空.", new Object[]{JSON.toJSONString(params), Boolean.valueOf(httpResponse.isOk()), rspJson});


            return;

        }
        /* 490 */
        JSONObject jObj = JSON.parseObject(rspJson);
        /* 491 */
        if (!jObj.containsKey("code") ||
                /* 492 */       !Integer.valueOf(200).equals(Integer.valueOf(jObj.getIntValue("code")))) {
            /* 493 */
            log.debug("查询交易数据, 请求入参[{}], 返回结果不正确[{}].", JSON.toJSONString(params), rspJson);

            return;

        }
        /* 496 */
        JSONArray jDataArr = jObj.getJSONArray("data");
        /* 497 */
        List<TrcTransferItemDTO> transferItemDTOList = jDataArr.toJavaList(TrcTransferItemDTO.class);
        /* 498 */
        if (ObjectUtil.isEmpty(transferItemDTOList)) {
            /* 499 */
            log.debug("查询交易数据, 结果为空[{}].", rspJson);

            return;

        }
        /* 502 */
        transferItemDTOList = (List<TrcTransferItemDTO>) transferItemDTOList.stream().filter(ts -> "SUCCESS".equalsIgnoreCase(ts.getContract_ret())).collect(Collectors.toList());
        /* 503 */
        if (ObjectUtil.isEmpty(transferItemDTOList)) {
            /* 504 */
            log.debug("查询交易数据, 结果成功部分为空[{}].", rspJson);


            return;

        }
        /* 508 */
        Map<String, TrcTransferItemDTO> hashMap = (Map<String, TrcTransferItemDTO>) transferItemDTOList.stream().collect(Collectors.toMap(TrcTransferItemDTO::getHash, Function.identity()));
        /* 509 */
        List<String> hashList = (List<String>) transferItemDTOList.stream().map(TrcTransferItemDTO::getHash).distinct().collect(Collectors.toList());


        /* 512 */
        RechargeQueryDTO rechargeQueryDTO = RechargeQueryDTO.builder().hashList(hashList).build();
        /* 513 */
        List<RechargeVo> rechargeVoList = this.rechargeApiService.queryRechargeList(rechargeQueryDTO);
        /* 514 */
        List<String> existsHashList = ListUtil.toList((String[]) new String[0]);
        /* 515 */
        if (ObjectUtil.isNotEmpty(rechargeVoList)) {

            /* 517 */
            List<String> dbExistsHashList = (List<String>) rechargeVoList.stream().map(RechargeVo::getHash).distinct().collect(Collectors.toList());
            /* 518 */
            if (ObjectUtil.isNotEmpty(dbExistsHashList)) {
                /* 519 */
                existsHashList = dbExistsHashList;

            }

        }

        /* 523 */
        for (TrcTransferItemDTO trcTransferItemDTO : transferItemDTOList) {
            /* 524 */
            if (existsHashList.contains(trcTransferItemDTO.getHash())) {

                continue;

            }

            /* 528 */
            if (rechargeVo.getAmount().compareTo(NumberUtil.div(trcTransferItemDTO.getAmount(), NumberUtil.toBigDecimal(StrUtil.padAfter("1", trcTransferItemDTO.getDecimals().intValue() + 1, "0")))) != 0) {

                continue;

            }

            /* 532 */
            ((RechargeCron) this.applicationContext.getBean(RechargeCron.class)).doReceiveUsdt(rechargeVo, trcTransferItemDTO.getHash(), trcTransferItemDTO
/* 533 */.getFrom(), paramsetVo);
            /* 534 */
            existsHashList.add(trcTransferItemDTO.getHash());

        }

    }
















    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doReceiveUsdt(RechargeVo rechargeVo, String hash, String from, ParamsetVo paramsetVo) {
        /* 553 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(rechargeVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).operateAmount(rechargeVo.getReceiveAmount()).bizId(rechargeVo.getId()).streamTypeEnum(StreamTypeEnum.RECHARGE).build();
        /* 554 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);






        /* 561 */
        RechargeSaveOrUpdateDTO rechargeSaveOrUpdateDTO = ((RechargeSaveOrUpdateDTO.RechargeSaveOrUpdateDTOBuilder) RechargeSaveOrUpdateDTO.builder().id(rechargeVo.getId())).hash(hash).fromAddress(from).status(RechargeStatusEnum.PAY_SUCCESS.getStatus()).build();
        /* 562 */
        this.rechargeApiService.saveOrUpdate(rechargeSaveOrUpdateDTO);





        /* 568 */
        WalletAddressSaveOrUpdateDTO walletAddressSaveOrUpdateDTO = ((WalletAddressSaveOrUpdateDTO.WalletAddressSaveOrUpdateDTOBuilder) WalletAddressSaveOrUpdateDTO.builder().id(rechargeVo.getWalletId())).addressStatus(AddressStatusEnum.NORMAL.getStatus()).clearLockTime(true).build();
        /* 569 */
        this.walletAddressApiService.saveOrUpdate(walletAddressSaveOrUpdateDTO);


        /* 572 */
        this.dingdingMsgSender.sendMsg(
                /* 573 */         ProxyMsgDTO.builder()
/* 574 */.accountId(rechargeVo.getAccountId())
/* 575 */.msg("账户[" + rechargeVo.getAccountName() + "], 充值[" + rechargeVo.getAmount().stripTrailingZeros().toPlainString() + "]成功.")
/* 576 */.build());

    }

}


