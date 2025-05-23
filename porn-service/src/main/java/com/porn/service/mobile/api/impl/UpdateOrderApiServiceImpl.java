package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderSaveOrUpdateDTO;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderApiServiceImpl
        implements ApiService<OrderVo> {

    @Autowired
    private OrderApiService orderApiService;


    public OrderVo cmd(CmdRequestDTO cmdRequestDTO) {

        OrderSaveOrUpdateDTO orderSaveOrUpdateDTO = (OrderSaveOrUpdateDTO) JSON.parseObject(cmdRequestDTO.getData(), OrderSaveOrUpdateDTO.class);

        if (ObjectUtil.isEmpty(orderSaveOrUpdateDTO.getId())) {

            return null;

        }

        return this.orderApiService.saveOrUpdate(orderSaveOrUpdateDTO);

    }


    public String getApi() {

        return "api_updateorder";

    }


    public boolean validateToken() {

        return Boolean.FALSE.booleanValue();

    }

}

