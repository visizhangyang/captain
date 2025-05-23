package com.porn.service.autowork.cron;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.autowork.vo.AutoWorkAccountVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamSaveOrUpdateDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
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
import java.util.Arrays;
import java.util.List;


@Component
public class AutoWorkStatusCron
        implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(AutoWorkStatusCron.class);


    @Autowired
    private OrderApiService orderApiService;


    @Autowired
    private AccountApiService accountApiService;


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private StreamApiService streamApiService;


    @Autowired
    private ParamsetApiService paramsetApiService;

    private ApplicationContext applicationContext;

    @Scheduled(cron = "0/20 * * * * ?")
    public void doCompare() {

        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatusList(Arrays.asList(new Integer[]{OrderStatusEnum.WAIT_PAY.getStatus(), OrderStatusEnum.PAY_SUCCESS.getStatus()})).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();

        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);

        if (ObjectUtil.isEmpty(orderVoList)) {

            return;

        }


        for (OrderVo orderVo : orderVoList) {


            AutoWorkAccountVo autoWorkAccountVo = isMatchAutoWork(orderVo);

            if (ObjectUtil.isEmpty(autoWorkAccountVo)) {

                continue;

            }

            if (!isMatchTime(orderVo, autoWorkAccountVo)) {

                continue;

            }


            ((AutoWorkStatusCron) this.applicationContext.getBean(AutoWorkStatusCron.class)).doProcess(orderVo, autoWorkAccountVo);

        }

    }

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
    public void doProcess(OrderVo orderVo, AutoWorkAccountVo autoWorkAccountVo) {

        if (orderVo.getOrderStatus() == OrderStatusEnum.PAY_SUCCESS.getStatus()) {

            doAttachProcess(orderVo, autoWorkAccountVo);

            clearOrderCache(orderVo);

        }


        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = ((OrderSaveOrUpdateDTO.OrderSaveOrUpdateDTOBuilder) OrderSaveOrUpdateDTO.builder().id(orderVo.getId())).orderStatus((orderVo.getOrderStatus() == OrderStatusEnum.WAIT_PAY.getStatus()) ? OrderStatusEnum.PAY_SUCCESS.getStatus() : OrderStatusEnum.CONFIRED.getStatus()).build();

        this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);

    }


    public void doAttachProcess(OrderVo orderVo, AutoWorkAccountVo autoWorkAccountVo) {

        this.streamApiService.saveOrUpdate(
                StreamSaveOrUpdateDTO.builder()
                        .accountId(orderVo.getAccountId()).accountName(orderVo.getAccountName())
                        .beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
                        .afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
                        .bizId(orderVo.getId())
                        .amount(orderVo.getOrderAmount())
                        .type(StreamTypeEnum.WORKING_SUB.getType())
                        .flag(StreamTypeEnum.WORKING_SUB.getFlag())
                        .build());

        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_ADD).operateAmount(orderVo.getFreeAmount()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        proxyFreeProcess(orderVo);

    }


    public boolean isMatchTime(OrderVo orderVo, AutoWorkAccountVo autoWorkAccountVo) {

        LocalDateTime now = LocalDateTimeUtil.now();

        if (orderVo.getOrderStatus() == OrderStatusEnum.WAIT_PAY.getStatus()) {

            if (now.compareTo(autoWorkAccountVo.getNextLoanTime()) >= 0) {

                return true;

            }

        }

        if (orderVo.getOrderStatus() == OrderStatusEnum.PAY_SUCCESS.getStatus()) {

            if (now.compareTo(autoWorkAccountVo.getNextCompleteTime()) >= 0) {

                return true;

            }

        }

        return false;

    }

    public AutoWorkAccountVo isMatchAutoWork(OrderVo orderVo) {

        String cacheOrderKey = String.format("AUTOWORKORDER%s%s", new Object[]{String.valueOf(orderVo.getAccountId()), String.valueOf(orderVo.getId())});

        String autoWorkInfoJson = (String) this.redisTemplate.opsForValue().get(cacheOrderKey);

        if (ObjectUtil.isEmpty(autoWorkInfoJson)) {

            return null;

        }

        return (AutoWorkAccountVo) JSON.parseObject(autoWorkInfoJson, AutoWorkAccountVo.class);

    }


    public void clearOrderCache(OrderVo orderVo) {

        String cacheOrderKey = String.format("AUTOWORKORDER%s%s", new Object[]{String.valueOf(orderVo.getAccountId()), String.valueOf(orderVo.getId())});

        this.redisTemplate.delete(cacheOrderKey);

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


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;

    }

}

