package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.MerchantTradeRequestDTO;
import com.porn.client.mobile.vo.MerchantTradeItemVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryPageDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MerchantTrade101ApiServiceImpl
        implements ApiService<List<MerchantTradeItemVo>> {

    @Autowired
    private OrderApiService orderApiService;

    @Autowired
    private MobileConverter mobileConverter;


    public List<MerchantTradeItemVo> cmd(CmdRequestDTO cmdRequestDTO) {

        MerchantTradeRequestDTO merchantTradeRequestDTO = (MerchantTradeRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), MerchantTradeRequestDTO.class);

        OrderQueryPageDTO orderQueryPageDTO = ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) OrderQueryPageDTO.builder().merchantId(merchantTradeRequestDTO.getMerchantId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).pageStart(Integer.valueOf(ObjectUtil.isEmpty(merchantTradeRequestDTO.getPageStart()) ? 1 : merchantTradeRequestDTO.getPageStart().intValue()))).pageSize(Integer.valueOf(ObjectUtil.isEmpty(merchantTradeRequestDTO.getPageSize()) ? 50 : merchantTradeRequestDTO.getPageSize().intValue()))).build();

        PageVo<OrderVo> orderVoPage = this.orderApiService.queryPage(orderQueryPageDTO);

        List<MerchantTradeItemVo> result = new ArrayList<>();

        if (ObjectUtil.isNotEmpty(orderVoPage) &&
                ObjectUtil.isNotEmpty(orderVoPage.getData())) {

            result = this.mobileConverter.toMerchantTradeItemVoList(orderVoPage.getData());

        }

        return result;

    }


    public String getApi() {

        return "api_merchanttrade";

    }


    public String getVersion() {

        return "1.0.1";

    }

}

