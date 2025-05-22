
package com.porn.service.mobile.api.impl;



import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.merchant.api.MerchantApiService;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.ConfirmOrderApiRequestDTO;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.dingdingmsg.dto.ProxyMsgDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.Arrays;









































@Service
 public class ConfirmOrderApiServiceImpl
         implements ApiService<OrderVo>
         {

    @Autowired
     private OrderApiService orderApiService;

    @Autowired
     private AccountApiService accountApiService;

    @Autowired
     private ParamsetApiService paramsetApiService;

    @Autowired
     private MerchantApiService merchantApiService;

    @Autowired
     private DingdingMsgSender dingdingMsgSender;



    public OrderVo cmd(CmdRequestDTO cmdRequestDTO) {
        /*  58 */
        ConfirmOrderApiRequestDTO confirmOrderApiRequestDTO = (ConfirmOrderApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), ConfirmOrderApiRequestDTO.class);



        /*  62 */
        OrderQueryDTO orderQueryDTO = ((OrderQueryDTO.OrderQueryDTOBuilder) OrderQueryDTO.builder().id(confirmOrderApiRequestDTO.getOrderId())).build();
        /*  63 */
        OrderVo orderVo = this.orderApiService.queryOrder(orderQueryDTO);
        /*  64 */
        if (ObjectUtil.isEmpty(orderVo)) {
            /*  65 */
            throw new BusinessException("订单信息不存在.");

        }
        /*  67 */
        if (OrderStatusEnum.CONFIRED.getStatus().equals(orderVo.getOrderStatus()) || OrderStatusEnum.PAY_TIMEOUT
/*  68 */.getStatus().equals(orderVo.getOrderStatus()))
             {
            /*  70 */
            return orderVo;

        }








        /*  80 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_ADD).operateAmount(orderVo.getFreeAmount()).build();
        /*  81 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);


        /*  84 */
        proxyFreeProcess(orderVo);






        /*  91 */
        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = ((OrderSaveOrUpdateDTO.OrderSaveOrUpdateDTOBuilder) OrderSaveOrUpdateDTO.builder().id(confirmOrderApiRequestDTO.getOrderId())).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).freeAmount(accountAmountOperateDTO.getOperateAmount()).build();
        /*  92 */
        OrderVo newOrderVo = this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);


        /*  95 */
        this.dingdingMsgSender.sendMsg(
                /*  96 */         ProxyMsgDTO.builder()
/*  97 */.accountId(newOrderVo.getAccountId())
/*  98 */.msg("账户[" + newOrderVo.getAccountName() + "], 金额[" + newOrderVo.getOrderAmount().stripTrailingZeros().toPlainString() + "]已确认收款, 请核对.")
/*  99 */.build());


        /* 102 */
        return newOrderVo;

    }








    protected void proxyFreeProcess(OrderVo orderVo) {
        /* 111 */
        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(orderVo.getAccountId())).build());
        /* 112 */
        if (ObjectUtil.isEmpty(accountVo.getParentId())) {

            return;

        }


        /* 117 */
        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        /* 120 */
        AccountVo parentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{accountVo.getParentId()})).build());
        /* 121 */
        if (ObjectUtil.isEmpty(parentAccountVo)) {

            return;

        }







        /* 131 */
        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_1).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel1Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 132 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        /* 133 */
        if (ObjectUtil.isEmpty(parentAccountVo.getParentId())) {

            return;

        }


        /* 138 */
        AccountVo parentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentAccountVo.getParentId()})).build());
        /* 139 */
        if (ObjectUtil.isEmpty(parentParentAccountVo)) {

            return;

        }






        /* 148 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_2).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel2Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 149 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);
        /* 150 */
        if (ObjectUtil.isEmpty(parentParentAccountVo.getParentId())) {

            return;

        }


        /* 155 */
        AccountVo parentParentParentAccountVo = this.accountApiService.queryAccount(AccountQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{parentParentAccountVo.getParentId()})).build());
        /* 156 */
        if (ObjectUtil.isEmpty(parentParentParentAccountVo)) {

            return;

        }






        /* 165 */
        accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(parentParentParentAccountVo.getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.PROXY_3).operateAmount(NumberUtil.mul(orderVo.getOrderAmount(), NumberUtil.div(paramsetVo.getProxyLevel3Rate(), Integer.valueOf(100))).setScale(2, RoundingMode.HALF_UP)).build();
        /* 166 */
        this.accountApiService.operateAmount(accountAmountOperateDTO);

    }




    public String getApi() {
        /* 171 */
        return "api_confirmorder";

    }

}


