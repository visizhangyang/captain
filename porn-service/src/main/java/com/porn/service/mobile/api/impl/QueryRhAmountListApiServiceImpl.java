package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.rhamount.api.RhAmountApiService;
import com.porn.client.rhamount.dto.RhAmountQueryDTO;
import com.porn.client.rhamount.vo.RhAmountVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryRhAmountListApiServiceImpl
        implements ApiService<List<Integer>> {

    @Autowired
    private RhAmountApiService rhAmountApiService;


    public List<Integer> cmd(CmdRequestDTO cmdRequestDTO) {

        List<RhAmountVo> rhAmountVoList = this.rhAmountApiService.queryRhAmountList(RhAmountQueryDTO.builder().build());

        return ObjectUtil.isEmpty(rhAmountVoList) ? Collections.<Integer>emptyList() :
                (List<Integer>) rhAmountVoList.stream().map(v -> Integer.valueOf(v.getAmount().intValue())).collect(Collectors.toList());

    }


    public String getApi() {

        return "api_queryrhamountlist";

    }

}

