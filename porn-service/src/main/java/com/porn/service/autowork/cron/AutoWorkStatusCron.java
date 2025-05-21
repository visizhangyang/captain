
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
         implements ApplicationContextAware
         {
    /*  46 */   private static final Logger log = LoggerFactory.getLogger(AutoWorkStatusCron.class);



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
        /*  74 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatusList(Arrays.asList(new Integer[]{OrderStatusEnum.WAIT_PAY.getStatus(), OrderStatusEnum.PAY_SUCCESS.getStatus()})).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();
        /*  75 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
        /*  76 */
        if (ObjectUtil.isEmpty(orderVoList)) {

            return;

        }

        /*  80 */
        for (OrderVo orderVo : orderVoList) {

            /*  82 */
            AutoWorkAccountVo autoWorkAccountVo = isMatchAutoWork(orderVo);
            /*  83 */
            if (ObjectUtil.isEmpty(autoWorkAccountVo)) {

                continue;

            }
            /*  86 */
            if (!isMatchTime(orderVo, autoWorkAccountVo)) {

                continue;

            }


            /*  91 */
            ((AutoWorkStatusCron) this.applicationContext.getBean(AutoWorkStatusCron.class)).doProcess(orderVo, autoWorkAccountVo);

        }

    }








    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     public void doProcess(OrderVo orderVo, AutoWorkAccountVo autoWorkAccountVo) {
        /* 102 */
        if (orderVo.getOrderStatus() == OrderStatusEnum.PAY_SUCCESS.getStatus()) {

            /* 104 */
            doAttachProcess(orderVo, autoWorkAccountVo);

            /* 106 */
            clearOrderCache(orderVo);

        }




        /* 112 */
        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = ((OrderSaveOrUpdateDTO.OrderSaveOrUpdateDTOBuilder) OrderSaveOrUpdateDTO.builder().id(orderVo.getId())).orderStatus((orderVo.getOrderStatus() == OrderStatusEnum.WAIT_PAY.getStatus()) ? OrderStatusEnum.PAY_SUCCESS.getStatus() : OrderStatusEnum.CONFIRED.getStatus()).build();
        /* 113 */
        this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);

    }









    public void doAttachProcess(OrderVo orderVo, AutoWorkAccountVo autoWorkAccountVo) {
        /* 123 */
        this.streamApiService.saveOrUpdate(
                /* 124 */         StreamSaveOrUpdateDTO.builder()
/* 125 */.accountId(orderVo.getAccountId()).accountName(orderVo.getAccountName())
/* 126 */.beforeTotalBalance(BigDecimal.ZERO).beforeAvailableBalance(BigDecimal.ZERO).beforeFreezeBalance(BigDecimal.ZERO)
/* 127 */.afterTotalBalance(BigDecimal.ZERO).afterAvailableBalance(BigDecimal.ZERO).afterFreezeBalance(BigDecimal.ZERO)
/* 128 */.bizId(orderVo.getId())
/* 129 */.amount(orderVo.getOrderAmount())
/* 130 */.type(StreamTypeEnum.WORKING_SUB.getType())
/* 131 */.flag(StreamTypeEnum.WORKING_SUB.getFlag())
/* 132 */.build());









        /* 142 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_ADD).operateAmount(orderVo.getFreeAmount()).build();
        /* 143 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);


        /* 146 */
        proxyFreeProcess(orderVo);

    }









    public boolean isMatchTime(OrderVo orderVo, AutoWorkAccountVo autoWorkAccountVo) {
        /* 156 */
        LocalDateTime now = LocalDateTimeUtil.now();
        /* 157 */
        if (orderVo.getOrderStatus() == OrderStatusEnum.WAIT_PAY.getStatus())
             {
            /* 159 */
            if (now.compareTo(autoWorkAccountVo.getNextLoanTime()) >= 0) {
                /* 160 */
                return true;

            }

        }
        /* 163 */
        if (orderVo.getOrderStatus() == OrderStatusEnum.PAY_SUCCESS.getStatus())
             {
            /* 165 */
            if (now.compareTo(autoWorkAccountVo.getNextCompleteTime()) >= 0) {
                /* 166 */
                return true;

            }

        }
        /* 169 */
        return false;

    }








    public AutoWorkAccountVo isMatchAutoWork(OrderVo orderVo) {
        /* 178 */
        String cacheOrderKey = String.format("AUTOWORKORDER%s%s", new Object[]{String.valueOf(orderVo.getAccountId()), String.valueOf(orderVo.getId())});
        /* 179 */
        String autoWorkInfoJson = (String) this.redisTemplate.opsForValue().get(cacheOrderKey);
        /* 180 */
        if (ObjectUtil.isEmpty(autoWorkInfoJson)) {
            /* 181 */
            return null;

        }
        /* 183 */
        return (AutoWorkAccountVo) JSON.parseObject(autoWorkInfoJson, AutoWorkAccountVo.class);

    }







    public void clearOrderCache(OrderVo orderVo) {
        /* 191 */
        String cacheOrderKey = String.format("AUTOWORKORDER%s%s", new Object[]{String.valueOf(orderVo.getAccountId()), String.valueOf(orderVo.getId())});
        /* 192 */
        this.redisTemplate.delete(cacheOrderKey);

    }








    protected void proxyFreeProcess(OrderVo orderVo) {
        /* 201 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(orderVo.getAccountId())).build());
        /* 202 */
        if (ObjectUtil.isEmpty(accountVo.getParentId())) {

            return;

        }


        /* 207 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        /* 210 */
        AccountVo parentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{accountVo.getParentId()})).build());
        /* 211 */
        if (ObjectUtil.isEmpty(parentAccountVo)) {

            return;

        }







        /* 221 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_1).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel1Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 222 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        /* 223 */
        if (ObjectUtil.isEmpty(parentAccountVo.getParentId())) {

            return;

        }


        /* 228 */
        AccountVo parentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentAccountVo.getParentId()})).build());
        /* 229 */
        if (ObjectUtil.isEmpty(parentParentAccountVo)) {

            return;

        }






        /* 238 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_2).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel2Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 239 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        /* 240 */
        if (ObjectUtil.isEmpty(parentParentAccountVo.getParentId())) {

            return;

        }


        /* 245 */
        AccountVo parentParentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentParentAccountVo.getParentId()})).build());
        /* 246 */
        if (ObjectUtil.isEmpty(parentParentParentAccountVo)) {

            return;

        }






        /* 255 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_3).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel3Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 256 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);

    }







    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 264 */
        this.applicationContext = applicationContext;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/autowork/cron/AutoWorkStatusCron.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */