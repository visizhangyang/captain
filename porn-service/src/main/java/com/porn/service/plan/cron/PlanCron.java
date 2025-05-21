
package com.porn.service.plan.cron;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.autowork.api.AutoWorkApiService;
import com.porn.client.autowork.vo.AutoWorkAccountVo;
import com.porn.client.common.constrant.CommonConst;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.enums.MerchantTypeEnum;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.enums.OrderTypeEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.plan.api.PlanInsApiService;
import com.porn.client.plan.dto.PlanInsQueryDTO;
import com.porn.client.plan.dto.PlanInsSaveOrUpdateDTO;
import com.porn.client.plan.enums.PlanInsStatusEnum;
import com.porn.client.plan.vo.PlanInsVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;









































































@Component
 public class PlanCron
         implements ApplicationContextAware, InitializingBean
         {
    /*  78 */   private static final Logger log = LoggerFactory.getLogger(PlanCron.class);



    @Autowired
     private PlanInsApiService planInsApiService;



    @Autowired
     private ParamsetApiService paramsetApiService;



    @Autowired
     private AccountApiService accountApiService;



    @Autowired
     private AutoWorkApiService autoWorkApiService;



    @Autowired
     private RedisTemplate redisTemplate;



    @Autowired
     private OrderApiService orderApiService;


    @Autowired
     private MerchantApiService merchantApiService;


    @Autowired
     private GoodsApiService goodsApiService;


    @Autowired
     private StreamApiService streamApiService;


    @Autowired
     private AccountExtApiService accountExtApiService;

    /* 116 */   private final List<MerchantVo> cacheMerchantVoList = new CopyOnWriteArrayList<>();

       private ApplicationContext applicationContext;



    @Scheduled(cron = "0 0/5 * * * ?")
     public void doCompare() {
        /* 122 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        /* 124 */
        String hour = LocalDateTimeUtil.format(LocalDateTime.now(), "HH");

        /* 126 */
        List<Integer> accountLevelList = new ArrayList<>();

        /* 128 */
        if (hour.compareTo(paramsetVo.getNormalLevelWorkMinRange()) >= 0 && hour
/* 129 */.compareTo(paramsetVo.getNormalLevelWorkMaxRange()) <= 0) {
            /* 130 */
            accountLevelList.add(AccountLevelEnum.NORMAL.getLevel());

        }
        /* 132 */
        if (hour.compareTo(paramsetVo.getLargeLevelWorkMinRange()) >= 0 && hour
/* 133 */.compareTo(paramsetVo.getLargeLevelWorkMaxRange()) <= 0) {
            /* 134 */
            accountLevelList.add(AccountLevelEnum.LARGE.getLevel());

        }
        /* 136 */
        if (hour.compareTo(paramsetVo.getPartnerLevelWorkMinRange()) >= 0 && hour
/* 137 */.compareTo(paramsetVo.getPartnerLevelWorkMaxRange()) <= 0) {
            /* 138 */
            accountLevelList.add(AccountLevelEnum.PARTNER.getLevel());

        }
        /* 140 */
        if (ObjectUtil.isEmpty(accountLevelList)) {

            return;

        }





        /* 148 */
        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();
        /* 149 */
        List<PlanInsVo> planInsVoList = this.planInsApiService.queryPlanInsList(planInsQueryDTO);
        /* 150 */
        if (ObjectUtil.isEmpty(planInsVoList)) {

            return;

        }

        /* 154 */
        Map<Long, PlanInsVo> planInsVoMap = (Map<Long, PlanInsVo>) planInsVoList.stream().collect(Collectors.toMap(PlanInsVo::getAccountId, Function.identity()));
        /* 155 */
        List<Long> accountIdList = (List<Long>) planInsVoList.stream().map(PlanInsVo::getAccountId).distinct().collect(Collectors.toList());






        /* 162 */
        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountLevelList(accountLevelList).accountIdList(accountIdList).accountType(AccountTypeEnum.NORMAL.getType()).build();
        /* 163 */
        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);
        /* 164 */
        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }

        /* 168 */
        for (AccountVo accountVo : accountVoList) {

            try {
                /* 170 */
                if (ObjectUtil.isEmpty(accountVo.getAvatar()) ||
                        /* 171 */           ObjectUtil.isEmpty(accountVo.getReceiveAddress())) {
                    /* 172 */
                    log.debug("账户[]没有上传头像或地址, 无法自动计划搬砖.", accountVo.getName());

                    continue;

                }
                /* 175 */
                ((PlanCron) this.applicationContext.getBean(PlanCron.class)).doWorkInit(paramsetVo, accountVo, planInsVoMap.get(accountVo.getId()));

                try {
                    /* 177 */
                    Thread.sleep(1000L);
                    /* 178 */
                } catch (Exception exception) {
                }


            }
            /* 181 */ catch (Exception e) {
                /* 182 */
                log.debug(e.getMessage(), e);

            }

        }

    }










    public void doWorkInit(ParamsetVo paramsetVo, AccountVo accountVo, PlanInsVo planInsVo) {
        /* 195 */
        LocalDateTime now = LocalDateTimeUtil.now();
        /* 196 */
        String curHms = LocalDateTimeUtil.format(now, "HH");
        /* 197 */
        String minHms = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkMinRange() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkMinRange() : paramsetVo.getPartnerLevelWorkMinRange());
        /* 198 */
        String maxHms = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkMaxRange() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkMaxRange() : paramsetVo.getPartnerLevelWorkMaxRange());

        /* 200 */
        if (curHms.compareTo(minHms) < 0 || curHms
/* 201 */.compareTo(maxHms) > 0) {

            return;

        }
        /* 204 */
        String cacheKey = String.format("PLANWORK:%s:%s", new Object[]{LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd"), String.valueOf(accountVo.getId())});
        /* 205 */
        String autoWorkInfoJson = (String) this.redisTemplate.opsForValue().get(cacheKey);
        /* 206 */
        if (ObjectUtil.isEmpty(autoWorkInfoJson)) {

            /* 208 */
            AutoWorkAccountVo autoWorkAccountVo = buildAutoWorkAccountVo(paramsetVo, accountVo, cacheKey);

            /* 210 */
            this.redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

        } else {
            /* 212 */
            AutoWorkAccountVo autoWorkAccountVo = (AutoWorkAccountVo) JSON.parseObject(autoWorkInfoJson, AutoWorkAccountVo.class);

            /* 214 */
            if (autoWorkAccountVo.getWorkCount().compareTo(autoWorkAccountVo.getWorkTotalCount()) >= 0) {

                return;

            }

            /* 218 */
            long subMinutes = LocalDateTimeUtil.between(now, autoWorkAccountVo.getNextWorkTime(), ChronoUnit.MINUTES);
            /* 219 */
            if (subMinutes <= 0L)
                 {
                /* 221 */
                ((PlanCron) this.applicationContext.getBean(PlanCron.class)).doWork(paramsetVo, accountVo, autoWorkAccountVo, planInsVo);

            }

        }

    }










    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doWork(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo, PlanInsVo planInsVo) {
        /* 235 */
        if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {
            /* 236 */
            refreshCache();
            /* 237 */
            if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {

                return;

            }

        }


        /* 243 */
        MerchantVo merchantVo = RandomUtil.randomEles(this.cacheMerchantVoList, 1).get(0);
        /* 244 */
        BigDecimal amount = BigDecimal.ZERO;
        /* 245 */
        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                /* 246 */         AccountExtQueryDTO.builder()
/* 247 */.accountId(accountVo.getId())
/* 248 */.extType(AccountExtTypeEnum.PLANRATE.getType())
/* 249 */.extKey(AccountExtTypeEnum.PLANRATE.toString())
/* 250 */.build());

        /* 252 */
        if (ObjectUtil.isEmpty(accountExtVo)) {
            /* 253 */
            amount = RandomUtil.randomBigDecimal(NumberUtil.mul(planInsVo.getTotalInvest(), Double.valueOf(0.6D)), planInsVo.getTotalInvest()).setScale(0, RoundingMode.HALF_UP);

        } else {



            try {

                /* 259 */
                amount = RandomUtil.randomBigDecimal(NumberUtil.mul(planInsVo.getTotalInvest(), (new BigDecimal(accountExtVo.getAttr1())).movePointLeft(2)), NumberUtil.mul(planInsVo.getTotalInvest(), (new BigDecimal(accountExtVo.getAttr2())).movePointLeft(2))).setScale(0, RoundingMode.HALF_UP);
                /* 260 */
            } catch (Exception e) {
                /* 261 */
                log.error("账户[{}], 计划金额系数配置错误[{}], [{}].", new Object[]{accountVo.getName(), accountExtVo.getAttr1(), accountExtVo.getAttr2()});
                /* 262 */
                amount = RandomUtil.randomBigDecimal(NumberUtil.mul(planInsVo.getTotalInvest(), Double.valueOf(0.6D)), planInsVo.getTotalInvest()).setScale(0, RoundingMode.HALF_UP);

            }

        }
        /* 265 */
        GoodsVo goodsVo = createGoods(paramsetVo, merchantVo, amount);


        /* 268 */
        OrderVo orderVo = this.orderApiService.saveOrUpdate(
                /* 269 */         OrderSaveOrUpdateDTO.builder()
/* 270 */.accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar())
/* 271 */.merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar())
/* 272 */.orderAmount(goodsVo.getAmount())
/* 273 */.orderRate(goodsVo.getRate())
/* 274 */.freeAmount(buildFreeAmount(accountVo, paramsetVo, goodsVo, planInsVo))
/* 275 */.orderStatus(OrderStatusEnum.CONFIRED.getStatus())
/* 276 */.playStatus(CommonConst.IONE)
/* 277 */.orderType(OrderTypeEnum.NORMAL.getType())
/* 278 */.notifyMsg(Boolean.FALSE)
/* 279 */.build());






        /* 286 */
        PlanInsSaveOrUpdateDTO planInsSaveOrUpdateDTO = ((PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder().id(planInsVo.getId())).totalInvest(NumberUtil.add(orderVo.getFreeAmount(), planInsVo.getTotalInvest())).build();
        /* 287 */
        this.planInsApiService.saveOrUpdate(planInsSaveOrUpdateDTO);


        /* 290 */
        this.streamApiService.saveOrUpdate(
                /* 291 */         StreamSaveOrUpdateDTO.builder()
/* 292 */.accountId(accountVo.getId()).accountName(accountVo.getName())
/* 293 */.beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
/* 294 */.afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
/* 295 */.bizId(orderVo.getId())
/* 296 */.amount(orderVo.getFreeAmount())
/* 297 */.type(StreamTypeEnum.PLAN_PROFIT.getType())
/* 298 */.flag(StreamTypeEnum.PLAN_PROFIT.getFlag())
/* 299 */.build());



        /* 303 */
        proxyFreeProcess(orderVo);


        /* 306 */
        this.goodsApiService.saveOrUpdate((
                /* 307 */         (GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder()
/* 308 */.id(goodsVo.getId()))
/* 309 */.goodsStatus(GoodsStatusEnum.WORKED.getStatus())
/* 310 */.build());



        /* 314 */
        updateAutoWorkAccountVo(paramsetVo, accountVo, autoWorkAccountVo);

    }








    protected void proxyFreeProcess(OrderVo orderVo) {
        /* 323 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(orderVo.getAccountId())).build());
        /* 324 */
        if (ObjectUtil.isEmpty(accountVo.getParentId())) {

            return;

        }


        /* 329 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        /* 332 */
        AccountVo parentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{accountVo.getParentId()})).build());
        /* 333 */
        if (ObjectUtil.isEmpty(parentAccountVo)) {

            return;

        }







        /* 343 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_1).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel1Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 344 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        /* 345 */
        if (ObjectUtil.isEmpty(parentAccountVo.getParentId())) {

            return;

        }


        /* 350 */
        AccountVo parentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentAccountVo.getParentId()})).build());
        /* 351 */
        if (ObjectUtil.isEmpty(parentParentAccountVo)) {

            return;

        }






        /* 360 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_2).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel2Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 361 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        /* 362 */
        if (ObjectUtil.isEmpty(parentParentAccountVo.getParentId())) {

            return;

        }


        /* 367 */
        AccountVo parentParentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentParentAccountVo.getParentId()})).build());
        /* 368 */
        if (ObjectUtil.isEmpty(parentParentParentAccountVo)) {

            return;

        }






        /* 377 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_3).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel3Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 378 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);

    }










    protected void updateAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo) {
        /* 389 */
        LocalDateTime now = LocalDateTime.now();
        /* 390 */
        if (ObjectUtil.isEmpty(autoWorkAccountVo.getStartWorkTime())) {
            /* 391 */
            autoWorkAccountVo.setStartWorkTime(now);

        }
        /* 393 */
        autoWorkAccountVo.setLastWorkTime(now);
        /* 394 */
        autoWorkAccountVo.setWorkCount(Integer.valueOf(autoWorkAccountVo.getWorkCount().intValue() + 1));


        /* 397 */
        Integer workSpace = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkSpace() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkSpace() : paramsetVo.getPartnerLevelWorkSpace());
        /* 398 */
        Integer randomSpace = Integer.valueOf(RandomUtil.randomInt(5, 20));
        /* 399 */
        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, NumberUtil.add(workSpace, randomSpace).intValue(), ChronoUnit.MINUTES);
        /* 400 */
        autoWorkAccountVo.setNextWorkTime(nextWorkTime);


        /* 403 */
        this.redisTemplate.opsForValue().set(autoWorkAccountVo.getCacheKey(), JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

    }










    protected BigDecimal buildFreeAmount(AccountVo accountVo, ParamsetVo paramsetVo, GoodsVo goodsVo, PlanInsVo planInsVo) {
        /* 414 */
        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                /* 415 */         AccountExtQueryDTO.builder()
/* 416 */.accountId(accountVo.getId())
/* 417 */.extType(AccountExtTypeEnum.EXTRAREBATE.getType())
/* 418 */.extKey(AccountExtTypeEnum.EXTRAREBATE.toString())
/* 419 */.build());


        /* 422 */
        BigDecimal extraRebate = BigDecimal.ZERO;
        /* 423 */
        if (ObjectUtil.isNotEmpty(accountExtVo)) {


            try {
                /* 426 */
                extraRebate = new BigDecimal(accountExtVo.getExtValue());
                /* 427 */
            } catch (Exception e) {
                /* 428 */
                log.error("获取额外加成数据异常[{}].", e);
                /* 429 */
                extraRebate = BigDecimal.ZERO;

            }

        }
        /* 432 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())
            /* 433 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), planInsVo.getExtraBonus()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);
        /* 434 */
        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {
            /* 435 */
            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), planInsVo.getExtraBonus()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }
        /* 437 */
        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), planInsVo.getExtraBonus()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }



















    public GoodsVo createGoods(ParamsetVo paramsetVo, MerchantVo merchantVo, BigDecimal amount) {
        /* 457 */
        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();
        /* 458 */
        return this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

    }











    protected AutoWorkAccountVo buildAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, String cacheKey) {
        /* 470 */
        AutoWorkAccountVo.AutoWorkAccountVoBuilder builder = AutoWorkAccountVo.builder();

        /* 472 */
        Integer workTotalCount = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkCount() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkCount() : paramsetVo.getPartnerLevelWorkCount());






        /* 479 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(accountVo.getId()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.now())).build();
        /* 480 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);


        /* 483 */
        Integer spaceMins = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkSpace() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkSpace() : paramsetVo.getPartnerLevelWorkSpace());


        /* 486 */
        LocalDateTime now = LocalDateTimeUtil.now();
        /* 487 */
        Integer workSpace = Integer.valueOf(RandomUtil.randomInt(5, 20));
        /* 488 */
        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, workSpace.intValue(), ChronoUnit.MINUTES);
        /* 489 */
        return builder
/* 490 */.lastWorkTime(ObjectUtil.isEmpty(orderVoList) ? null : ((OrderVo) orderVoList.get(0)).getCreateTime())
/* 491 */.nextWorkTime(ObjectUtil.isEmpty(orderVoList) ? nextWorkTime : calcExistsOrderNext(spaceMins, ((OrderVo) orderVoList.get(0)).getCreateTime(), nextWorkTime, workSpace))
/* 492 */.workCount(Integer.valueOf(ObjectUtil.isEmpty(orderVoList) ? CommonConst.IZERO.intValue() : orderVoList.size()))
/* 493 */.workTotalCount(workTotalCount)
/* 494 */.cacheKey(cacheKey)
/* 495 */.build();

    }











    protected LocalDateTime calcExistsOrderNext(Integer spaceMins, LocalDateTime lastOrderTime, LocalDateTime nextWorkTime, Integer workSpace) {
        /* 507 */
        if (NumberUtil.compare(LocalDateTimeUtil.between(lastOrderTime, LocalDateTimeUtil.now(), ChronoUnit.MINUTES), Long.valueOf(spaceMins.intValue()).longValue()) >= 0) {
            /* 508 */
            return nextWorkTime;

        }
        /* 510 */
        return LocalDateTimeUtil.offset(lastOrderTime, NumberUtil.add(spaceMins, workSpace).intValue(), ChronoUnit.MINUTES);

    }





    @Scheduled(cron = "0 0 1 * * ?")
     public void doRefreshCache() {
        /* 517 */
        refreshCache();

    }






    public void refreshCache() {
        /* 524 */
        MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().merchantType(MerchantTypeEnum.NORMAL.getType()).build();
        /* 525 */
        List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);
        /* 526 */
        if (ObjectUtil.isNotEmpty(merchantVoList)) {
            /* 527 */
            this.cacheMerchantVoList.clear();
            /* 528 */
            this.cacheMerchantVoList.addAll(merchantVoList);

        }

    }



    public void afterPropertiesSet() throws Exception {
        /* 533 */
        refreshCache();

    }







    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 541 */
        this.applicationContext = applicationContext;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/plan/cron/PlanCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */