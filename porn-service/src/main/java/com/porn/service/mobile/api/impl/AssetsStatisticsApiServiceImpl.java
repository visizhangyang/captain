package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.AssetsStatisticsVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderQueryDTO;
import com.porn.client.order.enums.OrderStatusEnum;
import com.porn.client.order.vo.OrderVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


@Service
public class AssetsStatisticsApiServiceImpl
        implements ApiService<AssetsStatisticsVo> {

    @Autowired
    private OrderApiService orderApiService;

    @Autowired
    private StreamApiService streamApiService;


    public AssetsStatisticsVo cmd(CmdRequestDTO cmdRequestDTO) {

        OrderQueryDTO orderQueryDTO = OrderQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).orderStatus(OrderStatusEnum.CONFIRED.getStatus()).build();

        List<OrderVo> orderVoList = this.orderApiService.queryOrderList(orderQueryDTO);

        BigDecimal totalOrderAmount = BigDecimal.ZERO;

        BigDecimal totalOrderFree = BigDecimal.ZERO;

        BigDecimal totalTeamAmount = BigDecimal.ZERO;

        if (ObjectUtil.isNotEmpty(orderVoList)) {

            for (OrderVo orderVo : orderVoList) {

                totalOrderAmount = NumberUtil.add(totalOrderAmount, orderVo.getOrderAmount());

                totalOrderFree = NumberUtil.add(totalOrderFree, orderVo.getFreeAmount());

            }

        }


        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).typeList(Arrays.asList(new Integer[]{StreamTypeEnum.PROXY_1.getType(), StreamTypeEnum.PROXY_2.getType(), StreamTypeEnum.PROXY_3.getType()})).flag(StreamTypeEnum.PROXY_1.getFlag()).build();

        totalTeamAmount = this.streamApiService.statisticsTotalProxyProfit(streamQueryDTO);


        return AssetsStatisticsVo.builder()
                .totalOrderCount(Long.valueOf(ObjectUtil.isEmpty(orderVoList) ? 0L : orderVoList.size()))
                .totalOrderAmount(totalOrderAmount)
                .totalOrderFree(totalOrderFree)
                .totalTeamAmount(ObjectUtil.isEmpty(totalTeamAmount) ? BigDecimal.ZERO : totalTeamAmount)
                .build();

    }


    public String getApi() {

        return "api_assetsstatistics";

    }

}

