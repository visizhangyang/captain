package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.QueryWithdrawApiRequestDTO;
import com.porn.client.withdraw.api.WithdrawApiService;
import com.porn.client.withdraw.dto.WithdrawQueryDTO;
import com.porn.client.withdraw.vo.WithdrawVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class QueryWithdrawApiServiceImpl
        implements ApiService<List<WithdrawVo>> {

    @Autowired
    private WithdrawApiService withdrawApiService;


    public List<WithdrawVo> cmd(CmdRequestDTO cmdRequestDTO) {

        QueryWithdrawApiRequestDTO queryWithdrawApiRequestDTO = (QueryWithdrawApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), QueryWithdrawApiRequestDTO.class);


        WithdrawQueryDTO withdrawQueryDTO = WithdrawQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).startTime(LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), -7L, ChronoUnit.DAYS)).statusList(queryWithdrawApiRequestDTO.getStatusList()).build();

        if (ObjectUtil.isNotEmpty(queryWithdrawApiRequestDTO.getDateRange()) &&
                Integer.valueOf(0).equals(queryWithdrawApiRequestDTO.getDateRange())) {

            withdrawQueryDTO.setStartTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now()));

        }

        return this.withdrawApiService.queryWithdrawList(withdrawQueryDTO);

    }


    public String getApi() {

        return "api_querywithdraw";

    }

}

