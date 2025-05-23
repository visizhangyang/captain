package com.porn.service.mobile.api.impl;


import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.RechargeStatusApiRequestDTO;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RechargeStatusApiServiceImpl
        implements ApiService<RechargeVo> {

    @Autowired
    private RechargeApiService rechargeApiService;


    public RechargeVo cmd(CmdRequestDTO cmdRequestDTO) {

        RechargeStatusApiRequestDTO rechargeStatusApiRequestDTO = (RechargeStatusApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), RechargeStatusApiRequestDTO.class);


        RechargeQueryDTO rechargeQueryDTO = ((RechargeQueryDTO.RechargeQueryDTOBuilder) RechargeQueryDTO.builder().id(rechargeStatusApiRequestDTO.getRechargeId())).accountId(cmdRequestDTO.getAccountVo().getId()).build();

        return this.rechargeApiService.queryRecharge(rechargeQueryDTO);

    }


    public String getApi() {

        return "api_rechargestatus";

    }

}

