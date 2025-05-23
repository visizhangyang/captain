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
        implements ApiService<OrderVo> {

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

        ConfirmOrderApiRequestDTO confirmOrderApiRequestDTO = (ConfirmOrderApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), ConfirmOrderApiRequestDTO.class);


        OrderQueryDTO orderQueryDTO = ((OrderQueryDTO.OrderQueryDTOBuilder) OrderQueryDTO.builder().id(confirmOrderApiRequestDTO.getOrderId())).build();

        OrderVo orderVo = this.orderApiService.queryOrder(orderQueryDTO);

        if (ObjectUtil.isEmpty(orderVo)) {

            throw new BusinessException("订单信息不存在.");

        }

        if (OrderStatusEnum.CONFIRED.getStatus().equals(orderVo.getOrderStatus()) || OrderStatusEnum.PAY_TIMEOUT
                .getStatus().equals(orderVo.getOrderStatus())) {

            return orderVo;

        }


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(orderVo.getAccountId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).bizId(orderVo.getId()).streamTypeEnum(StreamTypeEnum.WORKING_ADD).operateAmount(orderVo.getFreeAmount()).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);


        proxyFreeProcess(orderVo);


        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = ((OrderSaveOrUpdateDTO.OrderSaveOrUpdateDTOBuilder) OrderSaveOrUpdateDTO.builder().id(confirmOrderApiRequestDTO.getOrderId())).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).freeAmount(accountAmountOperateDTO.getOperateAmount()).build();

        OrderVo newOrderVo = this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);


        this.dingdingMsgSender.sendMsg(
                ProxyMsgDTO.builder()
                        .accountId(newOrderVo.getAccountId())
                        .msg("账户[" + newOrderVo.getAccountName() + "], 金额[" + newOrderVo.getOrderAmount().stripTrailingZeros().toPlainString() + "]已确认收款, 请核对.")
                        .build());


        return newOrderVo;

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

    public String getApi() {

        return "api_confirmorder";

    }

}

