package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.NumberUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountStatisticsDTO;
import com.porn.client.account.vo.AccountStatisticsVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.PlatformStatisticsVo;
import com.porn.client.order.api.OrderApiService;
import com.porn.client.order.dto.OrderStatisticsDTO;
import com.porn.client.order.vo.OrderStatisticsVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformStatisticsApiServiceImpl
        implements ApiService<PlatformStatisticsVo> {

    @Autowired
    private OrderApiService orderApiService;

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private MobileConverter mobileConverter;

    @Autowired
    private ParamsetApiService paramsetApiService;


    public PlatformStatisticsVo cmd(CmdRequestDTO cmdRequestDTO) {

        AccountStatisticsVo accountStatisticsVo = this.accountApiService.accountStatistics(AccountStatisticsDTO.builder().build());


        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        OrderStatisticsVo orderStatisticsVo = this.orderApiService.orderStatistics(OrderStatisticsDTO.builder().build());

        return PlatformStatisticsVo.builder()
                .totalAccountCount(Long.valueOf(NumberUtil.add(accountStatisticsVo.getTotalAccountCount(), paramsetVo.getRegisterCount()).longValue()))
                .todayAccountCount(accountStatisticsVo.getTodayAccountCount())
                .totalOrderCount(orderStatisticsVo.getTotalOrderCount())
                .totalOrderAmount(orderStatisticsVo.getTotalOrderAmount())
                .build();

    }


    public String getApi() {

        return "api_platformstatistics";

    }

}

