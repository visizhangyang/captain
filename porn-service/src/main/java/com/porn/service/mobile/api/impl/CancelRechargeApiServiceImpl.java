package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.common.exceptions.BusinessException;
import com.porn.client.mobile.dto.CancelRechargeApiRequestDTO;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.CreateRechargeVo;
import com.porn.client.recharge.api.RechargeApiService;
import com.porn.client.recharge.dto.RechargeCancelDTO;
import com.porn.client.recharge.dto.RechargeQueryDTO;
import com.porn.client.recharge.vo.RechargeVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CancelRechargeApiServiceImpl
        implements ApiService<CreateRechargeVo> {

    @Autowired
    private RechargeApiService rechargeApiService;

    @Autowired
    private MobileConverter mobileConverter;


    public CreateRechargeVo cmd(CmdRequestDTO cmdRequestDTO) {

        CancelRechargeApiRequestDTO cancelRechargeApiRequestDTO = (CancelRechargeApiRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), CancelRechargeApiRequestDTO.class);

        if (ObjectUtil.isEmpty(cancelRechargeApiRequestDTO.getRechargeId())) {

            throw new BusinessException("充值ID不能为空.");

        }

        RechargeQueryDTO rechargeQueryDTO = ((RechargeQueryDTO.RechargeQueryDTOBuilder) RechargeQueryDTO.builder().id(cancelRechargeApiRequestDTO.getRechargeId())).accountId(cmdRequestDTO.getAccountVo().getId()).build();

        RechargeVo rechargeVo = this.rechargeApiService.queryRecharge(rechargeQueryDTO);

        if (ObjectUtil.isEmpty(rechargeVo)) {

            throw new BusinessException("充值信息不存在.");

        }


        RechargeCancelDTO rechargeCancelDTO = ((RechargeCancelDTO.RechargeCancelDTOBuilder) RechargeCancelDTO.builder().id(rechargeVo.getId())).build();

        this.rechargeApiService.cancel(rechargeCancelDTO);

        return this.mobileConverter.toCreateRechargeVo(rechargeVo);

    }


    public String getApi() {

        return "api_cancelrecharge";

    }

}

