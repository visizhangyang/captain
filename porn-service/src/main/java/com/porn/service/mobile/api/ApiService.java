package com.porn.service.mobile.api;


import com.porn.client.mobile.dto.CmdRequestDTO;

public interface ApiService<R> {
    R cmd(CmdRequestDTO paramCmdRequestDTO);


    default String getApi() {

        return "";

    }


    default String getVersion() {

        return "1.0.0";

    }


    default boolean validateToken() {

        return true;

    }

}
