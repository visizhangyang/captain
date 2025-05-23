package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.QueryOrderApiRequestDTO;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryPageDTO;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class QuerySimpleOrderApiServiceImpl
        implements ApiService<List<OrderVo>> {

    @Autowired
    private OrderApiService orderApiService;


    public List<OrderVo> cmd(CmdRequestDTO cmdRequestDTO) {

        QueryOrderApiRequestDTO queryOrderApiRequestDTO = (QueryOrderApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), QueryOrderApiRequestDTO.class);


        OrderQueryPageDTO orderQueryDTO = ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) OrderQueryPageDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).merchantId(queryOrderApiRequestDTO.getMerchantId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -15L, ChronoUnit.DAYS)).orderStatus(queryOrderApiRequestDTO.getOrderStatus()).orderStatusList(queryOrderApiRequestDTO.getOrderStatusList()).pageStart(queryOrderApiRequestDTO.getPageStart())).pageSize(queryOrderApiRequestDTO.getPageSize())).build();

        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getDateRange())) {

            if (Integer.valueOf(0).equals(queryOrderApiRequestDTO.getDateRange())) {

                orderQueryDTO.setStartTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now()));

            }

            if (Integer.valueOf(1).equals(queryOrderApiRequestDTO.getDateRange())) {

                orderQueryDTO.setStartTime(DateUtil.beginOfWeek(new Date()).toLocalDateTime());

            }

            if (Integer.valueOf(2).equals(queryOrderApiRequestDTO.getDateRange())) {

                orderQueryDTO.setStartTime(DateUtil.beginOfMonth(new Date()).toLocalDateTime());

            }

            if (Integer.valueOf(-1).equals(queryOrderApiRequestDTO.getDateRange())) {

                orderQueryDTO.setStartTime(null);

            }

        }

        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getQueryAllAccount()) && queryOrderApiRequestDTO
                .getQueryAllAccount().booleanValue()) {

            orderQueryDTO.setAccountId(null);

        }

        PageVo<OrderVo> pageVo = this.orderApiService.queryPage(orderQueryDTO);

        return (ObjectUtil.isEmpty(pageVo) || ObjectUtil.isEmpty(pageVo.getData())) ? Collections.<OrderVo>emptyList() : pageVo.getData();

    }


    public String getApi() {

        return "api_simpleorder";

    }


    public boolean validateToken() {

        return Boolean.FALSE.booleanValue();

    }

}

