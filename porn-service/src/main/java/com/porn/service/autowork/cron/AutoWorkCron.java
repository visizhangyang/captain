
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
    /*  70 */   private static final Logger log = LoggerFactory.getLogger(AutoWorkCron.class);



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

    /* 100 */   private final List<MerchantVo> cacheMerchantVoList = new CopyOnWriteArrayList<>();


       private ApplicationContext applicationContext;




    @Scheduled(cron = "0/5 * * * * ?")
     public void doCompare() {
        /* 108 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        /* 110 */
        String hour = LocalDateTimeUtil.format(LocalDateTime.now(), "HH");

        /* 112 */
        List<Integer> accountLevelList = new ArrayList<>();

        /* 114 */
        if (hour.compareTo(paramsetVo.getNormalLevelWorkMinRange()) >= 0 && hour
/* 115 */.compareTo(paramsetVo.getNormalLevelWorkMaxRange()) <= 0) {
            /* 116 */
            accountLevelList.add(AccountLevelEnum.NORMAL.getLevel());

        }
        /* 118 */
        if (hour.compareTo(paramsetVo.getLargeLevelWorkMinRange()) >= 0 && hour
/* 119 */.compareTo(paramsetVo.getLargeLevelWorkMaxRange()) <= 0) {
            /* 120 */
            accountLevelList.add(AccountLevelEnum.LARGE.getLevel());

        }
        /* 122 */
        if (hour.compareTo(paramsetVo.getPartnerLevelWorkMinRange()) >= 0 && hour
/* 123 */.compareTo(paramsetVo.getPartnerLevelWorkMaxRange()) <= 0) {
            /* 124 */
            accountLevelList.add(AccountLevelEnum.PARTNER.getLevel());

        }
        /* 126 */
        if (ObjectUtil.isEmpty(accountLevelList)) {

            return;

        }







        /* 136 */
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountLevelList(accountLevelList).accountTypeList(Arrays.asList(new Integer[]{AccountTypeEnum.NORMAL.getType(), AccountTypeEnum.ROBOT.getType()})).autoWork(EnableStatusEnum.ENABLE.getStatus()).build();
        /* 137 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
        /* 138 */
        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }


        /* 143 */
        for (AccountVo accountVo : accountVoList) {


            try {
                /* 146 */
                ((AutoWorkCron) this.applicationContext.getBean(AutoWorkCron.class)).doWorkInit(paramsetVo, accountVo);

            }
            /* 148 */ catch (Exception e) {
                /* 149 */
                log.debug(e.getMessage(), e);

            }

        }

    }










    public void doWorkInit(ParamsetVo paramsetVo, AccountVo accountVo) {
        /* 162 */
        AutoWorkVo autoWorkVo = this.autoWorkApiService.queryAutoWork(AutoWorkQueryDTO.builder().accountId(accountVo.getId()).build());
        /* 163 */
        if (ObjectUtil.isEmpty(autoWorkVo)) {

            return;

        }


        /* 168 */
        LocalDateTime now = LocalDateTimeUtil.now();
        /* 169 */
        String curHms = LocalDateTimeUtil.format(now, "HHmmss");
        /* 170 */
        String minHms = LocalDateTimeUtil.format(autoWorkVo.getMinWorkTime(), "HHmmss");
        /* 171 */
        String maxHms = LocalDateTimeUtil.format(autoWorkVo.getMaxWorkTime(), "HHmmss");

        /* 173 */
        if (curHms.compareTo(minHms) < 0 || curHms
/* 174 */.compareTo(maxHms) > 0) {

            return;

        }


        /* 179 */
        String cacheKey = String.format("AUTOWORK%s%s", new Object[]{LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd"), String.valueOf(accountVo.getId())});
        /* 180 */
        String autoWorkInfoJson = (String) this.redisTemplate.opsForValue().get(cacheKey);
        /* 181 */
        if (ObjectUtil.isEmpty(autoWorkInfoJson)) {

            /* 183 */
            AutoWorkAccountVo autoWorkAccountVo = buildAutoWorkAccountVo(paramsetVo, accountVo, autoWorkVo, cacheKey);

            /* 185 */
            this.redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

        } else {

            /* 188 */
            AutoWorkAccountVo autoWorkAccountVo = (AutoWorkAccountVo) JSON.parseObject(autoWorkInfoJson, AutoWorkAccountVo.class);


            /* 191 */
            if (autoWorkAccountVo.getWorkCount().compareTo(autoWorkAccountVo.getWorkTotalCount()) >= 0) {

                return;

            }

            /* 195 */
            long subWorkMinutes = LocalDateTimeUtil.between(now, autoWorkAccountVo.getNextWorkTime(), ChronoUnit.SECONDS);
            /* 196 */
            if (subWorkMinutes <= 0L)
                 {

                /* 199 */
                ((AutoWorkCron) this.applicationContext.getBean(AutoWorkCron.class)).doWork(paramsetVo, accountVo, autoWorkAccountVo, autoWorkVo);

            }

        }

    }









    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doWork(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo, AutoWorkVo autoWorkVo) {
        /* 212 */
        if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {
            /* 213 */
            refreshCache();
            /* 214 */
            if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {

                return;

            }

        }


        /* 220 */
        MerchantVo merchantVo = RandomUtil.randomEles(this.cacheMerchantVoList, 1).get(0);
        /* 221 */
        BigDecimal amount = RandomUtil.randomBigDecimal(autoWorkVo.getMinWorkAmount(), autoWorkVo.getMaxWorkAmount()).setScale(0, RoundingMode.HALF_UP);
        /* 222 */
        GoodsVo goodsVo = createGoods(paramsetVo, merchantVo, amount);


        /* 225 */
        OrderVo orderVo = this.orderApiService.saveOrUpdate(
                /* 226 */         OrderSaveOrUpdateDTO.builder()
/* 227 */.accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar())
/* 228 */.merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar())
/* 229 */.orderAmount(goodsVo.getAmount())
/* 230 */.orderRate(goodsVo.getRate())
/* 231 */.freeAmount(buildFreeAmount(accountVo, paramsetVo, goodsVo))
/* 232 */.orderStatus(OrderStatusEnum.WAIT_PAY.getStatus())
/* 233 */.playStatus(CommonConst.IONE)
/* 234 */.orderType(OrderTypeEnum.ROBOT.getType())
/* 235 */.walletName(WalletChainEnum.TRON.getName())
/* 236 */.walletCode(WalletChainEnum.TRON.getCode())

/* 238 */.address(getAccountAddress(accountVo))
/* 239 */.notifyMsg(Boolean.FALSE)
/* 240 */.build());



        /* 244 */
        String cacheOrderKey = String.format("AUTOWORKORDER%s%s", new Object[]{String.valueOf(accountVo.getId()), String.valueOf(orderVo.getId())});
        /* 245 */
        this.redisTemplate.opsForValue().set(cacheOrderKey, JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);


        /* 248 */
        this.goodsApiService.saveOrUpdate((
                /* 249 */         (GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder()
/* 250 */.id(goodsVo.getId()))
/* 251 */.goodsStatus(GoodsStatusEnum.WORKED.getStatus())
/* 252 */.build());



        /* 256 */
        updateAutoWorkAccountVo(paramsetVo, accountVo, autoWorkAccountVo, autoWorkVo);

    }








    protected String getAccountAddress(AccountVo accountVo) {
        /* 265 */
        if (AccountTypeEnum.NORMAL.getType().equals(accountVo.getAccountType())) {

            /* 267 */
            if (ObjectUtil.isEmpty(accountVo.getReceiveAddress())) {




                /* 272 */
                AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = ((AccountSaveOrUpdateDTO.AccountSaveOrUpdateDTOBuilder) AccountSaveOrUpdateDTO.builder().id(accountVo.getId())).receiveAddress(TrcUtil.genTrc20Address()).build();
                /* 273 */
                this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);
                /* 274 */
                return accountSaveOrUpdateDTO.getReceiveAddress();

            }

            /* 277 */
            return accountVo.getReceiveAddress();

        }


        /* 281 */
        Date now = new Date();
        /* 282 */
        DateTime startTime = DateUtil.beginOfWeek(now);
        /* 283 */
        DateTime endTime = DateUtil.endOfWeek(now);
        /* 284 */
        String cacheKey = String.format("AUTOWORKACCOUNTWEEK%s%s%s", new Object[]{DateUtil.format((Date) startTime, "yyyyMMdd"), DateUtil.format((Date) endTime, "yyyyMMdd"), String.valueOf(accountVo.getId())});
        /* 285 */
        String receiveAddress = (String) this.redisTemplate.opsForValue().get(cacheKey);
        /* 286 */
        if (ObjectUtil.isEmpty(receiveAddress)) {
            /* 287 */
            receiveAddress = TrcUtil.genTrc20Address();
            /* 288 */
            this.redisTemplate.opsForValue().set(cacheKey, receiveAddress, 8L, TimeUnit.DAYS);

        }
        /* 290 */
        return receiveAddress;

    }











    protected BigDecimal buildFreeAmount(AccountVo accountVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {
        /* 302 */
        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                /* 303 */         AccountExtQueryDTO.builder()
/* 304 */.accountId(accountVo.getId())
/* 305 */.extType(AccountExtTypeEnum.EXTRAREBATE.getType())
/* 306 */.extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
/* 307 */.build());


        /* 310 */
        BigDecimal extraRebate = BigDecimal.ZERO;
        /* 311 */
        if (ObjectUtil.isNotEmpty(accountExtVo)) {


            try {
                /* 314 */
                extraRebate = new BigDecimal(accountExtVo.getExtValue());
                /* 315 */
            } catch (Exception e) {

                /* 317 */
                extraRebate = BigDecimal.ZERO;

            }

        }
        /* 320 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())
            /* 321 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);
        /* 322 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {
            /* 323 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }
        /* 325 */
        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }



















    public GoodsVo createGoods(ParamsetVo paramsetVo, MerchantVo merchantVo, BigDecimal amount) {
        /* 345 */
        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();
        /* 346 */
        return this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

    }











    protected void updateAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo, AutoWorkVo autoWorkVo) {
        /* 358 */
        LocalDateTime now = autoWorkAccountVo.getNextCompleteTime();
        /* 359 */
        if (ObjectUtil.isEmpty(autoWorkAccountVo.getStartWorkTime())) {
            /* 360 */
            autoWorkAccountVo.setStartWorkTime(now);

        }
        /* 362 */
        autoWorkAccountVo.setLastWorkTime(now);
        /* 363 */
        autoWorkAccountVo.setWorkCount(Integer.valueOf(autoWorkAccountVo.getWorkCount().intValue() + 1));


        /* 366 */
        Integer workSpace = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkSpace() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkSpace() : paramsetVo.getPartnerLevelWorkSpace());
        /* 367 */
        Integer randomSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinWorkSpace().intValue(), autoWorkVo.getMaxWorkSpace().intValue()));
        /* 368 */
        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, NumberUtil.add(workSpace, randomSpace).intValue(), ChronoUnit.MINUTES);
        /* 369 */
        autoWorkAccountVo.setNextWorkTime(nextWorkTime);


        /* 372 */
        Integer loanSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinLoanTime().intValue(), autoWorkVo.getMaxLoanTime().intValue()));
        /* 373 */
        LocalDateTime nextLoanTime = LocalDateTimeUtil.offset(nextWorkTime, loanSpace.intValue(), ChronoUnit.SECONDS);
        /* 374 */
        autoWorkAccountVo.setNextLoanTime(nextLoanTime);


        /* 377 */
        Integer completeSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinCompleteTime().intValue(), autoWorkVo.getMaxCompleteTime().intValue()));
        /* 378 */
        LocalDateTime nextCompleteTime = LocalDateTimeUtil.offset(nextLoanTime, loanSpace.intValue(), ChronoUnit.SECONDS);
        /* 379 */
        autoWorkAccountVo.setNextCompleteTime(nextCompleteTime);


        /* 382 */
        this.redisTemplate.opsForValue().set(autoWorkAccountVo.getCacheKey(), JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

    }










    protected AutoWorkAccountVo buildAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkVo autoWorkVo, String cacheKey) {
        /* 393 */
        AutoWorkAccountVo.AutoWorkAccountVoBuilder builder = AutoWorkAccountVo.builder();

        /* 395 */
        Integer workTotalCount = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinWorkCount().intValue(), autoWorkVo.getMaxWorkCount().intValue()));
        /* 396 */
        if (AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel())) {

            /* 398 */
            if (workTotalCount.compareTo(paramsetVo.getNormalLevelWorkCount()) > 0)
                 {
                /* 400 */
                workTotalCount = paramsetVo.getNormalLevelWorkCount();

            }
            /* 402 */
        } else if (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel())) {

            /* 404 */
            if (workTotalCount.compareTo(paramsetVo.getLargeLevelWorkCount()) > 0)
                 {
                /* 406 */
                workTotalCount = paramsetVo.getLargeLevelWorkCount();


            }

        }
        /* 410 */
        else if (workTotalCount.compareTo(paramsetVo.getPartnerLevelWorkCount()) > 0) {

            /* 412 */
            workTotalCount = paramsetVo.getPartnerLevelWorkCount();

        }


        /* 416 */
        LocalDateTime now = LocalDateTimeUtil.now();
        /* 417 */
        Integer workSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinWorkSpace().intValue(), autoWorkVo.getMaxWorkSpace().intValue()));
        /* 418 */
        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, workSpace.intValue(), ChronoUnit.MINUTES);


        /* 421 */
        Integer loanSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinLoanTime().intValue(), autoWorkVo.getMaxLoanTime().intValue()));
        /* 422 */
        LocalDateTime nextLoanTime = LocalDateTimeUtil.offset(nextWorkTime, loanSpace.intValue(), ChronoUnit.SECONDS);


        /* 425 */
        Integer completeSpace = Integer.valueOf(RandomUtil.randomInt(autoWorkVo.getMinCompleteTime().intValue(), autoWorkVo.getMaxCompleteTime().intValue()));
        /* 426 */
        LocalDateTime nextCompleteTime = LocalDateTimeUtil.offset(nextLoanTime, loanSpace.intValue(), ChronoUnit.SECONDS);

        /* 428 */
        return builder
/* 429 */.nextWorkTime(nextWorkTime)
/* 430 */.nextLoanTime(nextLoanTime)
/* 431 */.nextCompleteTime(nextCompleteTime)
/* 432 */.workCount(CommonConst.IZERO)
/* 433 */.workTotalCount(workTotalCount)
/* 434 */.cacheKey(cacheKey)
/* 435 */.build();

    }



    @Scheduled(cron = "0 0 1 * * ?")
     public void doRefreshCache() {
        /* 440 */
        refreshCache();

    }






    public void refreshCache() {
        /* 447 */
        MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().merchantType(MerchantTypeEnum.NORMAL.getType()).build();
        /* 448 */
        List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);
        /* 449 */
        if (ObjectUtil.isNotEmpty(merchantVoList)) {
            /* 450 */
            this.cacheMerchantVoList.clear();
            /* 451 */
            this.cacheMerchantVoList.addAll(merchantVoList);

        }

    }



    public void afterPropertiesSet() throws Exception {
        /* 456 */
        refreshCache();

    }






    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 463 */
        this.applicationContext = applicationContext;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/autowork/cron/AutoWorkCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */