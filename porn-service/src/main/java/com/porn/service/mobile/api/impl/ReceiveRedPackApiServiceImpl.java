package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountAmountOperateDTO;
import com.porn.client.account.enums.AmountTypeEnum;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.ReceiveRedPackVo;
import com.porn.client.paramset.api.ParamsetApiService;
import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveRedPackApiServiceImpl
        implements ApiService<ReceiveRedPackVo> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private ParamsetApiService paramsetApiService;

    @Autowired
    private StreamApiService streamApiService;


    public ReceiveRedPackVo cmd(CmdRequestDTO cmdRequestDTO) {

        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).flag(StreamTypeEnum.REDENVELOPE.getFlag()).type(StreamTypeEnum.REDENVELOPE.getType()).bizId(Long.valueOf(-1L)).build();

        StreamVo streamVo = this.streamApiService.queryStream(streamQueryDTO);

        if (ObjectUtil.isNotEmpty(streamVo)) {

            return ReceiveRedPackVo.builder().build();

        }

        ParamsetVo paramsetVo = this.paramsetApiService.queryParamset(ParamsetQueryDTO.builder().build());


        AccountAmountOperateDTO accountAmountOperateDTO = ((AccountAmountOperateDTO.AccountAmountOperateDTOBuilder) AccountAmountOperateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).amountType(AmountTypeEnum.ADDTOTAL_ADDAVAILABLE.getType()).operateAmount(paramsetVo.getInitRedPack()).bizId(Long.valueOf(-1L)).streamTypeEnum(StreamTypeEnum.REDENVELOPE).build();

        this.accountApiService.operateAmount(accountAmountOperateDTO);

        return ReceiveRedPackVo.builder().build();

    }

    public String getApi() {

        return "api_receiveredpack";

    }

}

