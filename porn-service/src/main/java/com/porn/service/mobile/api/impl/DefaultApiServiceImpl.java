package com.porn.service.mobile.api.impl;


import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.stereotype.Service;

@Service
public class DefaultApiServiceImpl
        implements ApiService<String> {

    public String cmd(CmdRequestDTO cmdRequestDTO) {

        throw new RuntimeException("未知类型请求.");

    }


    public String getApi() {

        return "api_default";

    }


    public boolean validateToken() {

        return false;

    }

}

