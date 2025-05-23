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
        implements ApplicationContextAware, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(PlanCron.class);
    private final List<MerchantVo> cacheMerchantVoList = new CopyOnWriteArrayList<>();
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
    private ApplicationContext applicationContext;


    @Scheduled(cron = "0 0/5 * * * ?")
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


        PlanInsQueryDTO planInsQueryDTO = PlanInsQueryDTO.builder().status(PlanInsStatusEnum.PROGRESSING.getStatus()).build();

        List<PlanInsVo> planInsVoList = this.planInsApiService.queryPlanInsList(planInsQueryDTO);

        if (ObjectUtil.isEmpty(planInsVoList)) {

            return;

        }


        Map<Long, PlanInsVo> planInsVoMap = (Map<Long, PlanInsVo>) planInsVoList.stream().collect(Collectors.toMap(PlanInsVo::getAccountId, Function.identity()));

        List<Long> accountIdList = (List<Long>) planInsVoList.stream().map(PlanInsVo::getAccountId).distinct().collect(Collectors.toList());


        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountLevelList(accountLevelList).accountIdList(accountIdList).accountType(AccountTypeEnum.NORMAL.getType()).build();

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

        if (ObjectUtil.isEmpty(accountVoList)) {

            return;

        }


        for (AccountVo accountVo : accountVoList) {

            try {

                if (ObjectUtil.isEmpty(accountVo.getAvatar()) ||
                        ObjectUtil.isEmpty(accountVo.getReceiveAddress())) {

                    log.debug("账户[]没有上传头像或地址, 无法自动计划搬砖.", accountVo.getName());

                    continue;

                }

                ((PlanCron) this.applicationContext.getBean(PlanCron.class)).doWorkInit(paramsetVo, accountVo, planInsVoMap.get(accountVo.getId()));

                try {

                    Thread.sleep(1000L);

                } catch (Exception exception) {
                }

            } catch (Exception e) {

                log.debug(e.getMessage(), e);

            }

        }

    }

    public void doWorkInit(ParamsetVo paramsetVo, AccountVo accountVo, PlanInsVo planInsVo) {

        LocalDateTime now = LocalDateTimeUtil.now();

        String curHms = LocalDateTimeUtil.format(now, "HH");

        String minHms = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkMinRange() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkMinRange() : paramsetVo.getPartnerLevelWorkMinRange());

        String maxHms = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkMaxRange() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkMaxRange() : paramsetVo.getPartnerLevelWorkMaxRange());


        if (curHms.compareTo(minHms) < 0 || curHms
                .compareTo(maxHms) > 0) {

            return;

        }

        String cacheKey = String.format("PLANWORK:%s:%s", new Object[]{LocalDateTimeUtil.format(LocalDateTimeUtil.now(), "yyyyMMdd"), String.valueOf(accountVo.getId())});

        String autoWorkInfoJson = (String) this.redisTemplate.opsForValue().get(cacheKey);

        if (ObjectUtil.isEmpty(autoWorkInfoJson)) {


            AutoWorkAccountVo autoWorkAccountVo = buildAutoWorkAccountVo(paramsetVo, accountVo, cacheKey);


            this.redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

        } else {

            AutoWorkAccountVo autoWorkAccountVo = (AutoWorkAccountVo) JSON.parseObject(autoWorkInfoJson, AutoWorkAccountVo.class);


            if (autoWorkAccountVo.getWorkCount().compareTo(autoWorkAccountVo.getWorkTotalCount()) >= 0) {

                return;

            }


            long subMinutes = LocalDateTimeUtil.between(now, autoWorkAccountVo.getNextWorkTime(), ChronoUnit.MINUTES);

            if (subMinutes <= 0L) {

                ((PlanCron) this.applicationContext.getBean(PlanCron.class)).doWork(paramsetVo, accountVo, autoWorkAccountVo, planInsVo);

            }

        }

    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doWork(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo, PlanInsVo planInsVo) {

        if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {

            refreshCache();

            if (ObjectUtil.isEmpty(this.cacheMerchantVoList)) {

                return;

            }

        }


        MerchantVo merchantVo = RandomUtil.randomEles(this.cacheMerchantVoList, 1).get(0);

        BigDecimal amount = BigDecimal.ZERO;

        AccountExtVo accountExtVo = this.accountExtApiService.queryAccountExt(
                AccountExtQueryDTO.builder()
                        .accountId(accountVo.getId())
                        .extType(AccountExtTypeEnum.PLANRATE.getType())
                        .extKey(AccountExtTypeEnum.PLANRATE.toString())
                        .build());


        if (ObjectUtil.isEmpty(accountExtVo)) {

            amount = RandomUtil.randomBigDecimal(NumberUtil.mul(planInsVo.getTotalInvest(), Double.valueOf(0.6D)), planInsVo.getTotalInvest()).setScale(0, RoundingMode.HALF_UP);

        } else {


            try {


                amount = RandomUtil.randomBigDecimal(NumberUtil.mul(planInsVo.getTotalInvest(), (new BigDecimal(accountExtVo.getAttr1())).movePointLeft(2)), NumberUtil.mul(planInsVo.getTotalInvest(), (new BigDecimal(accountExtVo.getAttr2())).movePointLeft(2))).setScale(0, RoundingMode.HALF_UP);

            } catch (Exception e) {

                log.error("账户[{}], 计划金额系数配置错误[{}], [{}].", new Object[]{accountVo.getName(), accountExtVo.getAttr1(), accountExtVo.getAttr2()});

                amount = RandomUtil.randomBigDecimal(NumberUtil.mul(planInsVo.getTotalInvest(), Double.valueOf(0.6D)), planInsVo.getTotalInvest()).setScale(0, RoundingMode.HALF_UP);

            }

        }

        GoodsVo goodsVo = createGoods(paramsetVo, merchantVo, amount);


        OrderVo orderVo = this.orderApiService.saveOrUpdate(
                OrderSaveOrUpdateDTO.builder()
                        .accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar())
                        .merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar())
                        .orderAmount(goodsVo.getAmount())
                        .orderRate(goodsVo.getRate())
                        .freeAmount(buildFreeAmount(accountVo, paramsetVo, goodsVo, planInsVo))
                        .orderStatus(OrderStatusEnum.CONFIRED.getStatus())
                        .playStatus(CommonConst.IONE)
                        .orderType(OrderTypeEnum.NORMAL.getType())
                        .notifyMsg(Boolean.FALSE)
                        .build());


        PlanInsSaveOrUpdateDTO planInsSaveOrUpdateDTO = ((PlanInsSaveOrUpdateDTO.PlanInsSaveOrUpdateDTOBuilder) PlanInsSaveOrUpdateDTO.builder().id(planInsVo.getId())).totalInvest(NumberUtil.add(orderVo.getFreeAmount(), planInsVo.getTotalInvest())).build();

        this.planInsApiService.saveOrUpdate(planInsSaveOrUpdateDTO);


        this.streamApiService.saveOrUpdate(
                StreamSaveOrUpdateDTO.builder()
                        .accountId(accountVo.getId()).accountName(accountVo.getName())
                        .beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
                        .afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
                        .bizId(orderVo.getId())
                        .amount(orderVo.getFreeAmount())
                        .type(StreamTypeEnum.PLAN_PROFIT.getType())
                        .flag(StreamTypeEnum.PLAN_PROFIT.getFlag())
                        .build());


        proxyFreeProcess(orderVo);


        this.goodsApiService.saveOrUpdate((
                (GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder()
                        .id(goodsVo.getId()))
                .goodsStatus(GoodsStatusEnum.WORKED.getStatus())
                .build());


        updateAutoWorkAccountVo(paramsetVo, accountVo, autoWorkAccountVo);

    }

    protected void proxyFreeProcess(OrderVo orderVo) {

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(orderVo.getAccountId())).build());

        if (ObjectUtil.isEmpty(accountVo.getParentId())) {

            return;

        }


        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        AccountVo parentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{accountVo.getParentId()})).build());

        if (ObjectUtil.isEmpty(parentAccountVo)) {

            return;

        }


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_1).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel1Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);

        if (ObjectUtil.isEmpty(parentAccountVo.getParentId())) {

            return;

        }


        AccountVo parentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentAccountVo.getParentId()})).build());

        if (ObjectUtil.isEmpty(parentParentAccountVo)) {

            return;

        }


        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_2).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel2Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);

        if (ObjectUtil.isEmpty(parentParentAccountVo.getParentId())) {

            return;

        }


        AccountVo parentParentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentParentAccountVo.getParentId()})).build());

        if (ObjectUtil.isEmpty(parentParentParentAccountVo)) {

            return;

        }


        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_3).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel3Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);

    }

    protected void updateAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, AutoWorkAccountVo autoWorkAccountVo) {

        LocalDateTime now = LocalDateTime.now();

        if (ObjectUtil.isEmpty(autoWorkAccountVo.getStartWorkTime())) {

            autoWorkAccountVo.setStartWorkTime(now);

        }

        autoWorkAccountVo.setLastWorkTime(now);

        autoWorkAccountVo.setWorkCount(Integer.valueOf(autoWorkAccountVo.getWorkCount().intValue() + 1));


        Integer workSpace = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkSpace() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkSpace() : paramsetVo.getPartnerLevelWorkSpace());

        Integer randomSpace = Integer.valueOf(RandomUtil.randomInt(5, 20));

        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, NumberUtil.add(workSpace, randomSpace).intValue(), ChronoUnit.MINUTES);

        autoWorkAccountVo.setNextWorkTime(nextWorkTime);


        this.redisTemplate.opsForValue().set(autoWorkAccountVo.getCacheKey(), JSON.toJSONString(autoWorkAccountVo), 1L, TimeUnit.DAYS);

    }

    protected BigDecimal buildFreeAmount(AccountVo accountVo, ParamsetVo paramsetVo, GoodsVo goodsVo, PlanInsVo planInsVo) {

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

                log.error("获取额外加成数据异常[{}].", e);

                extraRebate = BigDecimal.ZERO;

            }

        }

        if (accountVo.getAccountLevel() == AccountLevelEnum.NORMAL.getLevel())

            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(NumberUtil.add(paramsetVo.getNormalLevelRate(), extraRebate), planInsVo.getExtraBonus()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        if (accountVo.getAccountLevel() == AccountLevelEnum.LARGE.getLevel()) {

            return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(NumberUtil.add(paramsetVo.getLargeLevelRate(), extraRebate), planInsVo.getExtraBonus()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

        }

        return NumberUtil.mul(goodsVo.getAmount(), NumberUtil.div(NumberUtil.add(NumberUtil.add(paramsetVo.getPartnerLevelRate(), extraRebate), planInsVo.getExtraBonus()), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP);

    }


    public GoodsVo createGoods(ParamsetVo paramsetVo, MerchantVo merchantVo, BigDecimal amount) {

        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();

        return this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

    }


    protected AutoWorkAccountVo buildAutoWorkAccountVo(ParamsetVo paramsetVo, AccountVo accountVo, String cacheKey) {

        AutoWorkAccountVo.AutoWorkAccountVoBuilder builder = AutoWorkAccountVo.builder();


        Integer workTotalCount = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkCount() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkCount() : paramsetVo.getPartnerLevelWorkCount());


        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(accountVo.getId()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).endTime(LocalDateTimeUtil.endOfDay(LocalDateTimeUtil.now())).build();

        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);


        Integer spaceMins = AccountLevelEnum.NORMAL.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getNormalLevelWorkSpace() : (AccountLevelEnum.LARGE.getLevel().equals(accountVo.getAccountLevel()) ? paramsetVo.getLargeLevelWorkSpace() : paramsetVo.getPartnerLevelWorkSpace());


        LocalDateTime now = LocalDateTimeUtil.now();

        Integer workSpace = Integer.valueOf(RandomUtil.randomInt(5, 20));

        LocalDateTime nextWorkTime = LocalDateTimeUtil.offset(now, workSpace.intValue(), ChronoUnit.MINUTES);

        return builder
                .lastWorkTime(ObjectUtil.isEmpty(orderVoList) ? null : ((OrderVo) orderVoList.get(0)).getCreateTime())
                .nextWorkTime(ObjectUtil.isEmpty(orderVoList) ? nextWorkTime : calcExistsOrderNext(spaceMins, ((OrderVo) orderVoList.get(0)).getCreateTime(), nextWorkTime, workSpace))
                .workCount(Integer.valueOf(ObjectUtil.isEmpty(orderVoList) ? CommonConst.IZERO.intValue() : orderVoList.size()))
                .workTotalCount(workTotalCount)
                .cacheKey(cacheKey)
                .build();

    }


    protected LocalDateTime calcExistsOrderNext(Integer spaceMins, LocalDateTime lastOrderTime, LocalDateTime nextWorkTime, Integer workSpace) {

        if (NumberUtil.compare(LocalDateTimeUtil.between(lastOrderTime, LocalDateTimeUtil.now(), ChronoUnit.MINUTES), Long.valueOf(spaceMins.intValue()).longValue()) >= 0) {

            return nextWorkTime;

        }

        return LocalDateTimeUtil.offset(lastOrderTime, NumberUtil.add(spaceMins, workSpace).intValue(), ChronoUnit.MINUTES);

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

