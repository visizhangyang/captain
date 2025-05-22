
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
         implements ApiService<List<OrderVo>>
         {

    @Autowired
     private OrderApiService orderApiService;



    public List<OrderVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 35 */
        QueryOrderApiRequestDTO queryOrderApiRequestDTO = (QueryOrderApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), QueryOrderApiRequestDTO.class);








        /* 44 */
        OrderQueryPageDTO orderQueryDTO = ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) OrderQueryPageDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).merchantId(queryOrderApiRequestDTO.getMerchantId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -15L, ChronoUnit.DAYS)).orderStatus(queryOrderApiRequestDTO.getOrderStatus()).orderStatusList(queryOrderApiRequestDTO.getOrderStatusList()).pageStart(queryOrderApiRequestDTO.getPageStart())).pageSize(queryOrderApiRequestDTO.getPageSize())).build();
        /* 45 */
        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getDateRange())) {

            /* 47 */
            if (Integer.valueOf(0).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 48 */
                orderQueryDTO.setStartTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now()));

            }

            /* 51 */
            if (Integer.valueOf(1).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 52 */
                orderQueryDTO.setStartTime(DateUtil.beginOfWeek(new Date()).toLocalDateTime());

            }

            /* 55 */
            if (Integer.valueOf(2).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 56 */
                orderQueryDTO.setStartTime(DateUtil.beginOfMonth(new Date()).toLocalDateTime());

            }

            /* 59 */
            if (Integer.valueOf(-1).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 60 */
                orderQueryDTO.setStartTime(null);

            }

        }
        /* 63 */
        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getQueryAllAccount()) && queryOrderApiRequestDTO
/* 64 */.getQueryAllAccount().booleanValue()) {
            /* 65 */
            orderQueryDTO.setAccountId(null);

        }
        /* 67 */
        PageVo<OrderVo> pageVo = this.orderApiService.queryPage(orderQueryDTO);
        /* 68 */
        return (ObjectUtil.isEmpty(pageVo) || ObjectUtil.isEmpty(pageVo.getData())) ? Collections.<OrderVo>emptyList() : pageVo.getData();

    }



    public String getApi() {
        /* 72 */
        return "api_simpleorder";

    }



    public boolean validateToken() {
        /* 76 */
        return Boolean.FALSE.booleanValue();

    }

}


