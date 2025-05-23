package com.porn.service.mobile.api.impl;


import com.alibaba.fastjson2.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.OrderAppNotifyVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderAppNotifyApiServiceImpl
        implements ApiService<OrderVo> {

    @Autowired
    private OrderApiService orderApiService;


    public OrderVo cmd(CmdRequestDTO cmdRequestDTO) {

        OrderAppNotifyVo orderAppNotifyVo = (OrderAppNotifyVo) JSON.parseObject(cmdRequestDTO.getData(), OrderAppNotifyVo.class);

        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = ((OrderSaveOrUpdateDTO.OrderSaveOrUpdateDTOBuilder) OrderSaveOrUpdateDTO.builder().id(orderAppNotifyVo.getOrderId())).playStatus(Integer.valueOf(1)).build();

        return this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);

    }


    public String getApi() {

        return "api_orderappnotify";

    }

}

