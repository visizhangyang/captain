package com.porn.service.mobile.api.impl;


import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QueryRecordApiServiceImpl
        implements ApiService<List<StreamVo>> {

    @Autowired
    private StreamApiService streamApiService;


    public List<StreamVo> cmd(CmdRequestDTO cmdRequestDTO) {

        StreamQueryDTO streamQueryDTO = (StreamQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), StreamQueryDTO.class);

        streamQueryDTO.setAccountId(cmdRequestDTO.getAccountVo().getId());

        return this.streamApiService.queryStreamList(streamQueryDTO);

    }


    public String getApi() {

        return "api_queryrecord";

    }

}

