
package com.porn.service.mobile.api.impl;



import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.QueryOrderApiRequestDTO;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.vo.OrderVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

























@Service
 public class QueryOrderApiServiceImpl
         implements ApiService<List<OrderVo>>
         {

    @Autowired
     private OrderApiService orderApiService;



    public List<OrderVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 34 */
        QueryOrderApiRequestDTO queryOrderApiRequestDTO = (QueryOrderApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), QueryOrderApiRequestDTO.class);





        /* 40 */
        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -15L, ChronoUnit.DAYS)).orderStatus(queryOrderApiRequestDTO.getOrderStatus()).orderStatusList(queryOrderApiRequestDTO.getOrderStatusList()).build();
        /* 41 */
        if (ObjectUtil.isNotEmpty(queryOrderApiRequestDTO.getDateRange())) {

            /* 43 */
            if (Integer.valueOf(0).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 44 */
                orderQueryDTO.setStartTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now()));

            }

            /* 47 */
            if (Integer.valueOf(1).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 48 */
                orderQueryDTO.setStartTime(DateUtil.beginOfWeek(new Date()).toLocalDateTime());

            }

            /* 51 */
            if (Integer.valueOf(2).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 52 */
                orderQueryDTO.setStartTime(DateUtil.beginOfMonth(new Date()).toLocalDateTime());

            }

            /* 55 */
            if (Integer.valueOf(-1).equals(queryOrderApiRequestDTO.getDateRange())) {
                /* 56 */
                orderQueryDTO.setStartTime(null);

            }

        }
        /* 59 */
        return this.orderApiService.queryOrderList(orderQueryDTO);

    }



    public String getApi() {
        /* 63 */
        return "api_queryorder";

    }

}


