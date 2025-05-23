package com.porn.service.mobile.api.impl;


import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.AccountTreasureVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class QueryTreasureApiServiceImpl
        implements ApiService<AccountTreasureVo> {

    @Autowired
    private StreamApiService streamApiService;


    public AccountTreasureVo cmd(CmdRequestDTO cmdRequestDTO) {

        BigDecimal todayReceive = BigDecimal.ZERO;

        BigDecimal totalReceive = BigDecimal.ZERO;


        StreamQueryDTO todayStreamQueryDTO = StreamQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{cmdRequestDTO.getAccountVo().getId()})).type(StreamTypeEnum.TREASURE.getType()).flag(StreamTypeEnum.TREASURE.getFlag()).startTime(LocalDateTimeUtil.beginOfDay(LocalDateTimeUtil.now())).build();

        StreamVo todayStreamVo = this.streamApiService.queryStream(todayStreamQueryDTO);

        if (ObjectUtil.isNotEmpty(todayStreamVo)) {

            todayReceive = todayStreamVo.getAmount();

        }


        StreamQueryDTO totalStreamQueryDTO = StreamQueryDTO.builder().accountIdList(Arrays.asList(new Long[]{cmdRequestDTO.getAccountVo().getId()})).type(StreamTypeEnum.TREASURE.getType()).flag(StreamTypeEnum.TREASURE.getFlag()).build();

        List<StreamVo> streamVoList = this.streamApiService.queryStreamList(totalStreamQueryDTO);

        totalReceive = ObjectUtil.isEmpty(streamVoList) ? BigDecimal.ZERO : streamVoList.stream().map(StreamVo::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);


        return AccountTreasureVo.builder()
                .todayReceive(todayReceive)
                .totalReceive(totalReceive)
                .build();

    }


    public String getApi() {

        return "api_querytreasure";

    }

}

