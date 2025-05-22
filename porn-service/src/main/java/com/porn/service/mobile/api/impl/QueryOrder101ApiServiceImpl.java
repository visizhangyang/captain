
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
 public class QueryOrder101ApiServiceImpl
         implements ApiService<List<OrderVo>>
         {

    @Autowired
     private OrderApiService orderApiService;



    public List<OrderVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 38 */
        QueryOrderApiRequestDTO queryOrderApiRequestDTO = (QueryOrderApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), QueryOrderApiRequestDTO.class);








        /* 47 */
        OrderQueryPageDTO orderQueryDTO = ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) ((OrderQueryPageDTO.OrderQueryPageDTOBuilder) OrderQueryPageDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).merchantId(queryOrderApiRequestDTO.getMerchantId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -15L, ChronoUnit.DAYS)).orderStatus(queryOrderApiRequestDTO.getOrderStatus()).orderStatusList(queryOrderApiRequestDTO.getOrderStatusList()).pageStart(queryOrderApiRequestDTO.getPageStart())).pageSize(queryOrderApiRequestDTO.getPageSize())).build();
        /* 48 */
        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getDateRange())) {

            /* 50 */
            if (Integer.valueOf(0).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 51 */
                orderQueryDTO.setStartTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now()));

            }

            /* 54 */
            if (Integer.valueOf(1).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 55 */
                orderQueryDTO.setStartTime(DateUtil.beginOfWeek(new Date()).toLocalDateTime());

            }

            /* 58 */
            if (Integer.valueOf(2).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 59 */
                orderQueryDTO.setStartTime(DateUtil.beginOfMonth(new Date()).toLocalDateTime());

            }

            /* 62 */
            if (Integer.valueOf(-1).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 63 */
                orderQueryDTO.setStartTime(null);

            }

        }
        /* 66 */
        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getQueryAllAccount()) && queryOrderApiRequestDTO
/* 67 */.getQueryAllAccount().booleanValue()) {
            /* 68 */
            orderQueryDTO.setAccountId(null);

        }
        /* 70 */
        PageVo<OrderVo> pageVo = this.orderApiService.queryPage(orderQueryDTO);
        /* 71 */
        return (ObjectUtil.isEmpty(pageVo) || ObjectUtil.isEmpty(pageVo.getData())) ? Collections.<OrderVo>emptyList() : pageVo.getData();

    }



    public String getApi() {
        /* 75 */
        return "api_queryorder";

    }



    public String getVersion() {
        /* 79 */
        return "1.0.1";

    }

}


