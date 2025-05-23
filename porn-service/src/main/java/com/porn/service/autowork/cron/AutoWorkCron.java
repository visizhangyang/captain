package com.porn.service.autowork.cron;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.autowork.api.AutoWorkApiService;
import com.porn.client.autowork.dto.AutoWorkQueryDTO;
import com.porn.client.autowork.vo.AutoWorkAccountVo;
import com.porn.client.autowork.vo.AutoWorkVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.common.enums.EnableStatusEnum;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.enums.MerchantTypeEnum;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.enums.OrderTypeEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.wallet.enums.WalletChainEnum;
import com.porn.service.common.utils.TrcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Component
public class AutoWorkCron
        implements ApplicationContextAware, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(AutoWorkCron.class);
    private final List<MerchantVo> cacheMerchantVoList = new CopyOnWriteArrayList<>();
    @Autowired
    private AccountApiService accountApiService;
    @Autowired
    private ParamsetApiService paramsetApiService;
    @Autowired
    private AutoWorkApiService autoWorkApiService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MerchantApiService merchantApiService;
    @Autowired
    private OrderApiService orderApiService;
    @Autowired
    private GoodsApiService goodsApiService;
    @Autowired
    private AccountExtApiService accountExtApiService;
    private ApplicationContext applicationContext;

    @Scheduled(cron = "0/5 * * * * ?")
    public void doCompare() {

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        String hour = LocalDateTimeUtil.format(LocalDateTime.now(), "HH");

        List<Integer> accountLevelList = new ArrayList<>();

        if (hour.compareTo(paramsetVo.getNormalLevelWorkMinRange()) >= 0 && hour
                .compareTo(paramsetVo.getNormalLevelWorkMaxRange()) <= 0) {

            accountLevelList.add(AccountLevelEnum.NORMAL.getLevel());

        }

        if (hour.compareTo(paramsetVo.getLargeLevelWorkMinRange()) >= 0 && hour
                .compareTo(paramsetVo.getLargeLevelWorkMaxRange()) <= 0) {

            accountLevelList.add(AccountLevelEnum.LARGE.getLevel());

        }

        if (hour.compareTo(paramsetVo.getPartnerLevelWorkMinRange()) >= 0 && hour
                .compareTo(paramsetVo.getPartnerLevelWorkMaxRange()) <= 0) {

            accountLevelList.add(AccountLevelEnum.PARTNER.getLevel());

        }

        if (ObjectUtil.isEmpty(accountLevelList)) {

            return;

        }

        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountLevelList(accountLevelList).accountTypeList(Arrays.asList(new Integer[]{AccountTypeEnum.NORMAL.getType(), AccountTypeEnum.ROBOT.getType()})).autoWork(EnableStatusEnum.ENABLE.getStatus()).build();

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }


        for (AccountVo accountVo : accountVoList) {

            try {

                ((AutoWorkCron) this.applicationContext.getBean(AutoWorkCron.class)).doWorkInit(paramsetVo, accountVo);

            } catch (Exception e) {

                log.debug(e.getMessage(), e);

            }

        }

    }

    public void doWorkInit(ParamsetVo paramsetVo, AccountVo accountVo) {

        AutoWorkVo autoWorkVo = this.autoWorkApiService.queryAutoWork(AutoWorkQueryDTO.builder().accountId(accountVo.getId()).build());

        if (ObjectUtil.isEmpty(autoWorkVo)) {

            return;

        }


        LocalDateTime now = LocalDateTimeUtil.now();

        String curHms = LocalDateTimeUtil.format(now, "HHmmss");

        String minHms = LocalDateTimeUtil.format(autoWorkVo.getMinWorkTime(), "HHmmss");

        String maxHms = LocalDateTimeUtil.format(autoWorkVo.getMaxWorkTime(), "HHmmss");

        if (curHms.compareTo(minHms) < 0 || curHms
                .compareTo(maxHms) > 0) {

            return;

        }


        String cacheKey = String.format("AUTOWORK%s%s", new Object[]{LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd"), String.valueOf(accountVo.getId())});

        String autoWorkInfoJson = (String) this.redisTemplate.opsForValue().get(cacheKey);

        if (ObjectUtil.isEmpty(autoWorkInfoJson)) {

            AutoWorkAccountVo autoWorkAccountVo = buildAutoWorkAccountVo(paramsetVo, accountVo, autoWorkVo, cacheKey);

            this.redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

        } else {

            AutoWorkAccountVo autoWorkAccountVo = (AutoWorkAccountVo) JSON.parseObject(autoWorkInfoJson, AutoWorkAccountVo.class);


            if (autoWorkAccountVo.getWorkCount().compareTo(autoWorkAccountVo.getWorkTotalCount()) >= 0) {

                return;

            }

            long subWorkMinutes = LocalDateTimeUtil.between(now, autoWorkAccountVo.getNextWorkTime(), ChronoUnit.SECONDS);

            if (subWorkMinutes <= 0L) {

                ((AutoWorkCron) this.applicationContext.getBean(AutoWorkCron.class)).doWork(paramsetVo, accountVo, autoWorkAccountVo, autoWorkVo);

            }

        }

    }


    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doWork(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo, AutoWorkVo autoWorkVo) {

        if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {

            refreshCache();

            if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {

                return;

            }

        }


        MerchantVo merchantVo = RandomUtil.randomEles(this.cacheMerchantVoList, 1).get(0);

        BigDecimal amount = RandomUtil.randomBigDecimal(autoWorkVo.getMinWorkAmount(), autoWorkVo.getMaxWorkAmount()).setScale(0, RoundingMode.HALF_UP);

        GoodsVo goodsVo = createGoods(paramsetVo, merchantVo, amount);


        OrderVo orderVo = this.orderApiService.saveOrUpdate(
                OrderSaveOrUpdateDTO.builder()
                        .accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar())
                        .merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar())
                        .orderAmount(goodsVo.getAmount())
                        .orderRate(goodsVo.getRate())
                        .freeAmount(buildFreeAmount(accountVo, paramsetVo, goodsVo))
                        .orderStatus(OrderStatusEnum.WAIT_PAY.getStatus())
                        .playStatus(CommonConst.IONE)
                        .orderType(OrderTypeEnum.ROBOT.getType())
                        .walletName(WalletChainEnum.TRON.getName())
                        .walletCode(WalletChainEnum.TRON.getCode())

                        .address(getAccountAddress(accountVo))
                        .notifyMsg(Boolean.FALSE)
                        .build());

        String cacheOrderKey = String.format("AUTOWORKORDER%s%s", new Object[]{String.valueOf(accountVo.getId()), String.valueOf(orderVo.getId())});

        this.redisTemplate.opsForValue().set(cacheOrderKey, JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);


        this.goodsApiService.saveOrUpdate((
                (GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder()
                        .id(goodsVo.getId()))
                .goodsStatus(GoodsStatusEnum.WORKED.getStatus())
                .build());

        updateAutoWorkAccountVo(paramsetVo, accountVo, autoWorkAccountVo, autoWorkVo);

    }

    protected String getAccountAddress(AccountVo accountVo) {

        if (AccountTypeEnum.NORMAL.getType().equals(accountVo.getAccountType())) {

            if (ObjectUtil.isEmpty(accountVo.getReceiveAddress())) {


                AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = ((AccountSaveOrUpdateDTO.AccountSaveOrUpdateDTOBuilder) AccountSaveOrUpdateDTO.builder().id(accountVo.getId())).receiveAddress(TrcUtil.genTrc20Address()).build();

                this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);

                return accountSaveOrUpdateDTO.getReceiveAddress();

            }

            return accountVo.getReceiveAddress();

        }


        Date now = new Date();

        DateTime startTime = DateUtil.beginOfWeek(now);

        DateTime endTime = DateUtil.endOfWeek(now);

        String cacheKey = String.format("AUTOWORKACCOUNTWEEK%s%s%s", new Object[]{DateUtil.format((Date) startTime, "yyyyMMdd"), DateUtil.format((Date) endTime, "yyyyMMdd"), String.valueOf(accountVo.getId())});

        String receiveAddress = (String) this.redisTemplate.opsForValue().get(cacheKey);

        if (ObjectUtil.isEmpty(receiveAddress)) {

            receiveAddress = TrcUtil.genTrc20Address();

            this.redisTemplate.opsForValue().set(cacheKey, receiveAddress, 8L, TimeUnit.DAYS);

        }

        return receiveAddress;

    }


    protected BigDecimal buildFreeAmount(AccountVo accountVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {

        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                AccountExtQueryDTO.builder()
                        .accountId(accountVo.getId())
                        .extType(AccountExtTypeEnum.EXTRAREBATE.getType())
                        .extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
                        .build());


        BigDecimal extraRebate = BigDecimal.ZERO;

        if (ObjectUtil.isNotEmpty(accountExtVo)) {

            try {

                extraRebate = new BigDecimal(accountExtVo.getExtValue());

            } catch (Exception e) {

                extraRebate = BigDecimal.ZERO;

            }

        }

        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())

            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {

            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }

        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }


    public GoodsVo createGoods(ParamsetVo paramsetVo, MerchantVo merchantVo, BigDecimal amount) {

        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();

        return this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

    }


    protected void updateAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo, AutoWorkVo autoWorkVo) {

        LocalDateTime now = autoWorkAccountVo.getNextCompleteTime();

        if (ObjectUtil.isEmpty(autoWorkAccountVo.getStartWorkTime())) {

            autoWorkAccountVo.setStartWorkTime(now);

        }

        autoWorkAccountVo.setLastWorkTime(now);

        autoWorkAccountVo.setWorkCount(Integer.valueOf(autoWorkAccountVo.getWorkCount().intValue() + 1));


        Integer workSpace = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkSpace() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkSpace() : paramsetVo.getPartnerLevelWorkSpace());

        Integer randomSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinWorkSpace().intValue(), autoWorkVo.getMaxWorkSpace().intValue()));

        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, NumberUtil.add(workSpace, randomSpace).intValue(), ChronoUnit.MINUTES);

        autoWorkAccountVo.setNextWorkTime(nextWorkTime);


        Integer loanSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinLoanTime().intValue(), autoWorkVo.getMaxLoanTime().intValue()));

        LocalDateTime nextLoanTime = LocalDateTimeUtil.offset(nextWorkTime, loanSpace.intValue(), ChronoUnit.SECONDS);

        autoWorkAccountVo.setNextLoanTime(nextLoanTime);


        Integer completeSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinCompleteTime().intValue(), autoWorkVo.getMaxCompleteTime().intValue()));

        LocalDateTime nextCompleteTime = LocalDateTimeUtil.offset(nextLoanTime, loanSpace.intValue(), ChronoUnit.SECONDS);

        autoWorkAccountVo.setNextCompleteTime(nextCompleteTime);


        this.redisTemplate.opsForValue().set(autoWorkAccountVo.getCacheKey(), JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

    }

    protected AutoWorkAccountVo buildAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkVo autoWorkVo, String cacheKey) {

        AutoWorkAccountVo.AutoWorkAccountVoBuilder builder = AutoWorkAccountVo.builder();

        Integer workTotalCount = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinWorkCount().intValue(), autoWorkVo.getMaxWorkCount().intValue()));

        if (AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel())) {

            if (workTotalCount.compareTo(paramsetVo.getNormalLevelWorkCount()) > 0) {

                workTotalCount = paramsetVo.getNormalLevelWorkCount();

            }

        } else if (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel())) {

            if (workTotalCount.compareTo(paramsetVo.getLargeLevelWorkCount()) > 0) {

                workTotalCount = paramsetVo.getLargeLevelWorkCount();

            }

        } else if (workTotalCount.compareTo(paramsetVo.getPartnerLevelWorkCount()) > 0) {

            workTotalCount = paramsetVo.getPartnerLevelWorkCount();

        }


        LocalDateTime now = LocalDateTimeUtil.now();

        Integer workSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinWorkSpace().intValue(), autoWorkVo.getMaxWorkSpace().intValue()));

        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, workSpace.intValue(), ChronoUnit.MINUTES);


        Integer loanSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinLoanTime().intValue(), autoWorkVo.getMaxLoanTime().intValue()));

        LocalDateTime nextLoanTime = LocalDateTimeUtil.offset(nextWorkTime, loanSpace.intValue(), ChronoUnit.SECONDS);


        Integer completeSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinCompleteTime().intValue(), autoWorkVo.getMaxCompleteTime().intValue()));

        LocalDateTime nextCompleteTime = LocalDateTimeUtil.offset(nextLoanTime, loanSpace.intValue(), ChronoUnit.SECONDS);

        return builder
                .nextWorkTime(nextWorkTime)
                .nextLoanTime(nextLoanTime)
                .nextCompleteTime(nextCompleteTime)
                .workCount(CommonConst.IZERO)
                .workTotalCount(workTotalCount)
                .cacheKey(cacheKey)
                .build();

    }


    @Scheduled(cron = "0 0 1 * * ?")
    public void doRefreshCache() {

        refreshCache();

    }

    public void refreshCache() {

        MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().merchantType(MerchantTypeEnum.NORMAL.getType()).build();

        List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);

        if (ObjectUtil.isNotEmpty(merchantVoList)) {

            this.cacheMerchantVoList.clear();

            this.cacheMerchantVoList.addAll(merchantVoList);

        }

    }


    public void afterPropertiesSet() throws Exception {

        refreshCache();

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

