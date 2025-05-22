
package com.porn.service.order.cron;



import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.enums.StreamTypeEnum;
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

import java.util.Arrays;
import java.util.List;





























@Component
 public class OrderCron
         implements ApplicationContextAware
         {
    /*  34 */   private static final Logger log = LoggerFactory.getLogger(OrderCron.class);


       private static final int EXPIRE_MIN = 30;



    @Autowired
     private OrderApiService orderApiService;



    @Autowired
     private ParamsetApiService paramsetApiService;



    @Autowired
     private AccountApiService accountApiService;


       private ApplicationContext applicationContext;





    @Scheduled(cron = "0/5 * * * * ?")
     public void doCompare() {
        /*  58 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().orderStatusList(Arrays.asList(new Integer[]{OrderStatusEnum.WAIT_PAY.getStatus()})).build();
        /*  59 */
        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);
        /*  60 */
        if (ObjectUtil.isEmpty(orderVoList)) {

            return;

        }

        /*  64 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());
        /*  65 */
        for (OrderVo orderVo : orderVoList) {
            /*  66 */
            if (isExpire(orderVo, paramsetVo)) {

                try {
                    /*  68 */
                    ((OrderCron) this.applicationContext.getBean(OrderCron.class)).doExpire(orderVo);
                    /*  69 */
                } catch (Exception e) {
                    /*  70 */
                    log.debug(e.getMessage(), e);

                }

            }

        }

    }














    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)
     protected void doExpire(OrderVo orderVo) {
        /*  89 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.ADDAVAILABLE_SUBFREEZE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_UNLOCK).operateAmount(orderVo.getOrderAmount()).build();
        /*  90 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);





        /*  96 */
        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = ((OrderSaveOrUpdateDTO.OrderSaveOrUpdateDTOBuilder) OrderSaveOrUpdateDTO.builder().id(orderVo.getId())).orderStatus(OrderStatusEnum.PAY_TIMEOUT.getStatus()).build();
        /*  97 */
        this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);

    }









    protected boolean isExpire(OrderVo orderVo, ParamsetVo paramsetVo) {
        /* 107 */
        return

                /* 109 */       (LocalDateTimeUtil.between(orderVo.getCreateTime(), LocalDateTimeUtil.now()).toMinutes() > (ObjectUtil.isEmpty(paramsetVo) ? 30L : ((Integer) ObjectUtil.defaultIfNull(paramsetVo.getOrderMatchTime(), Integer.valueOf(30))).intValue()));

    }








    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        /* 118 */
        this.applicationContext = applicationContext;

    }

}


