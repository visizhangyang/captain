package com.porn.service.workrobot.cron;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountExtApiService;
import com.porn.client.account.dto.AccountExtQueryDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AccountExtTypeEnum;
import com.porn.client.account.enums.AccountLevelEnum;
import com.porn.client.account.enums.AccountTypeEnum;
import com.porn.client.account.vo.AccountExtVo;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.goods.api.GoodsApiService;
import com.porn.client.goods.dto.GoodsSaveOrUpdateDTO;
import com.porn.client.goods.enums.GoodsStatusEnum;
import com.porn.client.goods.vo.GoodsVo;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.merchant.dto.MerchantQueryDTO;
import com.porn.client.merchant.vo.MerchantVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.enums.OrderTypeEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.workrobot.api.WorkrobotApiService;
import com.porn.client.workrobot.dto.WorkrobotQueryDTO;
import com.porn.client.workrobot.vo.WorkrobotVo;
import com.porn.common.spring.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

@Component
public class WorkrobotCron
        implements ApplicationContextAware, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(WorkrobotCron.class);
    private final List<MerchantVo> cacheMerchantVoList = new CopyOnWriteArrayList<>();
    private final List<AccountVo> cacheAccountVoList = new CopyOnWriteArrayList<>();
    @Autowired
    private WorkrobotApiService workrobotApiService;
    @Autowired
    private ParamsetApiService paramsetApiService;
    @Autowired
    private MerchantApiService merchantApiService;
    @Autowired
    private AccountApiService accountApiService;
    @Autowired
    private GoodsApiService goodsApiService;
    @Autowired
    private OrderApiService orderApiService;
    @Autowired
    private StreamApiService streamApiService;
    @Autowired
    private ThreadPoolUtil threadPoolUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AccountExtApiService accountExtApiService;
    private ApplicationContext applicationContext;

    public void doWork() {

        List<WorkrobotVo> workrobotVoList = this.workrobotApiService.queryWorkrobotList(WorkrobotQueryDTO.builder().build());

        if (ObjectUtil.isEmpty(workrobotVoList)) {

            return;

        }

        WorkrobotVo workrobotVo = matchWorkrobot(workrobotVoList);

        if (ObjectUtil.isEmpty(workrobotVo)) {

            return;

        }


        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());

        if (ObjectUtil.isEmpty(paramsetVo)) {

            return;

        }

        doRobotWork(paramsetVo, workrobotVo);

    }


    public void doRefreshCache() {

        refreshCache();

    }


    protected void doRobotWork(ParamsetVo paramsetVo, WorkrobotVo workrobotVo) {

        DateTime dateTime1 = DateUtil.parse(workrobotVo.getMinWorkTime(), "yyyy-MM-dd HH:mm:ss");

        DateTime dateTime2 = DateUtil.parse(workrobotVo.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");

        long second = DateUtil.between((Date) dateTime1, (Date) dateTime2, DateUnit.SECOND);

        BigDecimal secondTime = NumberUtil.div(Long.valueOf(second), new BigDecimal(10)).setScale(0, RoundingMode.HALF_DOWN);


        Integer orderCount = Integer.valueOf(NumberUtil.div(workrobotVo.getOrderCount(), secondTime).setScale(0, RoundingMode.HALF_DOWN).intValue());


        Integer dbCount = (Integer) this.redisTemplate.opsForValue().get(StrUtil.join("_", new Object[]{"robotprefix_", DateUtil.format((Date) dateTime1, "yyyyMMddHHmmss"), DateUtil.format((Date) dateTime2, "yyyyMMddHHmmss")}));

        if (ObjectUtil.isEmpty(dbCount)) {

            this.redisTemplate.opsForValue().set(StrUtil.join("_", new Object[]{"robotprefix_", DateUtil.format((Date) dateTime1, "yyyyMMddHHmmss"), DateUtil.format((Date) dateTime2, "yyyyMMddHHmmss")}), Integer.valueOf(0), 1L, TimeUnit.HOURS);

        } else {

            if (NumberUtil.compare(dbCount.intValue(), workrobotVo.getOrderCount().intValue()) >= 0) {

                return;

            }

            if (NumberUtil.compare(NumberUtil.add(dbCount, orderCount).intValue(), workrobotVo.getOrderCount().intValue()) > 0) {

                orderCount = Integer.valueOf(NumberUtil.sub(workrobotVo.getOrderCount(), dbCount).intValue());

            }

            Date now = new Date();

            DateTime dateTime3 = DateUtil.offsetSecond(now, 10);

            DateTime dateTime4 = DateUtil.parse(workrobotVo.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");

            dateTime4.setYear(now.getYear());

            dateTime4.setMonth(now.getMonth());

            dateTime4.setDate(now.getDate());

            if (DateUtil.compare((Date) dateTime3, (Date) dateTime4) > 0) {

                orderCount = Integer.valueOf(NumberUtil.sub(workrobotVo.getOrderCount(), dbCount).setScale(0, RoundingMode.HALF_DOWN).intValue());

            }

        }

        Integer newOrderCount = orderCount;

        this.threadPoolUtil.addTask(() -> doRobotWorkPlay(paramsetVo, workrobotVo, newOrderCount));

        this.redisTemplate.opsForValue().increment(StrUtil.join("_", new Object[]{"robotprefix_", DateUtil.format((Date) dateTime1, "yyyyMMddHHmmss"), DateUtil.format((Date) dateTime2, "yyyyMMddHHmmss")}), orderCount.intValue());

    }

    protected void doRobotWorkPlay(ParamsetVo paramsetVo, WorkrobotVo workrobotVo, Integer orderCount) {

        if (this.cacheMerchantVoList.size() == 0 || this.cacheAccountVoList
                .size() == 0) {

            return;

        }

        List<MerchantVo> merchantVoList = this.cacheMerchantVoList;

        List<AccountVo> accountVoList = this.cacheAccountVoList;

        for (int i = 0; i < orderCount.intValue(); i++) {

            try {

                MerchantVo merchantVo = RandomUtil.randomEles(merchantVoList, 1).get(0);

                AccountVo accountVo = RandomUtil.randomEles(accountVoList, 1).get(0);

                BigDecimal amount = RandomUtil.randomBigDecimal(workrobotVo.getMinWorkAmount(), workrobotVo.getMaxWorkAmount()).setScale(0, RoundingMode.HALF_UP);

                GoodsVo goodsVo = ((WorkrobotCron) this.applicationContext.getBean(WorkrobotCron.class)).createGoods(paramsetVo, merchantVo, amount);

                ((WorkrobotCron) this.applicationContext.getBean(WorkrobotCron.class)).doWorkGoods(paramsetVo, accountVo, merchantVo, goodsVo);

            } catch (Exception e) {

                log.error(e.getMessage(), e);

            }

        }

    }


    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doWorkGoods(ParamsetVo paramsetVo, AccountVo accountVo, MerchantVo merchantVo, GoodsVo goodsVo) {

        OrderVo orderVo = this.orderApiService.saveOrUpdate(
                OrderSaveOrUpdateDTO.builder()
                        .accountId(accountVo.getId()).accountName(accountVo.getName()).accountAvatar(accountVo.getAvatar())
                        .merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar())
                        .orderAmount(goodsVo.getAmount())
                        .orderRate(goodsVo.getRate())
                        .freeAmount(buildFreeAmount(accountVo, paramsetVo, goodsVo))
                        .orderStatus(OrderStatusEnum.CONFIRED.getStatus())
                        .orderType(OrderTypeEnum.ROBOT.getType())
                        .build());

        this.streamApiService.saveOrUpdate(
                StreamSaveOrUpdateDTO.builder()
                        .accountId(accountVo.getId()).accountName(accountVo.getName())
                        .beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
                        .afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
                        .bizId(orderVo.getId())
                        .amount(goodsVo.getAmount())
                        .type(StreamTypeEnum.WORKING_SUB.getType())
                        .flag(StreamTypeEnum.WORKING_SUB.getFlag())
                        .build());

        this.streamApiService.saveOrUpdate(
                StreamSaveOrUpdateDTO.builder()
                        .accountId(accountVo.getId()).accountName(accountVo.getName())
                        .beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
                        .afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
                        .bizId(orderVo.getId())
                        .amount(orderVo.getFreeAmount())
                        .type(StreamTypeEnum.WORKING_ADD.getType())
                        .flag(StreamTypeEnum.WORKING_ADD.getFlag())
                        .build());

        this.goodsApiService.saveOrUpdate((
                (GoodsSaveOrUpdateDTO.GoodsSaveOrUpdateDTOBuilder) GoodsSaveOrUpdateDTO.builder()
                        .id(goodsVo.getId()))
                .goodsStatus(GoodsStatusEnum.WORKED.getStatus())
                .build());

    }

    public BigDecimal buildFreeAmount(AccountVo accountVo, ParamsetVo paramsetVo, GoodsVo goodsVo) {

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

                log.debug("获取额外加成数据异常[{}].", e);

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

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public GoodsVo createGoods(ParamsetVo paramsetVo, MerchantVo merchantVo, BigDecimal amount) {

        GoodsSaveOrUpdateDTO goodsSaveOrUpdateDTO = GoodsSaveOrUpdateDTO.builder().merchantId(merchantVo.getId()).merchantName(merchantVo.getName()).merchantAvatar(merchantVo.getAvatar()).amount(amount).rate(BigDecimal.ZERO).freeAmount(BigDecimal.ZERO).goodsStatus(GoodsStatusEnum.WAIT_WORING.getStatus()).build();

        return this.goodsApiService.saveOrUpdate(goodsSaveOrUpdateDTO);

    }

    protected WorkrobotVo matchWorkrobot(List<WorkrobotVo> workrobotVoList) {

        if (ObjectUtil.isEmpty(workrobotVoList)) {

            return null;

        }

        for (WorkrobotVo workrobotVo : workrobotVoList) {

            if (matchWorkrobot(workrobotVo)) {

                return workrobotVo;

            }

        }

        return null;

    }


    protected boolean matchWorkrobot(WorkrobotVo workrobotVo) {

        if (ObjectUtil.isEmpty(workrobotVo)) {

            return false;

        }

        Date now = new Date();

        DateTime dateTime1 = DateUtil.parse(workrobotVo.getMinWorkTime(), "yyyy-MM-dd HH:mm:ss");

        dateTime1.setYear(now.getYear());

        dateTime1.setMonth(now.getMonth());

        dateTime1.setDate(now.getDate());

        DateTime dateTime2 = DateUtil.parse(workrobotVo.getMaxWorkTime(), "yyyy-MM-dd HH:mm:ss");

        dateTime2.setYear(now.getYear());

        dateTime2.setMonth(now.getMonth());

        dateTime2.setDate(now.getDate());

        return DateUtil.isIn(now, (Date) dateTime1, (Date) dateTime2);

    }

    public void afterPropertiesSet() throws Exception {

        refreshCache();

    }

    public void refreshCache() {

        MerchantQueryDTO merchantQueryDTO = MerchantQueryDTO.builder().build();

        List<MerchantVo> merchantVoList = this.merchantApiService.queryMerchantList(merchantQueryDTO);

        if (ObjectUtil.isNotEmpty(merchantVoList)) {

            this.cacheMerchantVoList.clear();

            this.cacheMerchantVoList.addAll(merchantVoList);

        }

        AccountQueryDTO accountQueryDTO = AccountQueryDTO.builder().accountType(AccountTypeEnum.ROBOT.getType()).build();

        List<AccountVo> accountVoList = this.accountApiService.queryAccountList(accountQueryDTO);

        if (ObjectUtil.isNotEmpty(accountVoList)) {

            this.cacheAccountVoList.clear();

            this.cacheAccountVoList.addAll(accountVoList);

        }

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

