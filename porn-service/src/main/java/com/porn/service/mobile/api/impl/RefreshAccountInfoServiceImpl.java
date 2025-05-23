package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.api.AccountWalletService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.dto.AccountWalletQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.account.vo.AccountWalletVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.vo.LoginAccountVo;
import com.porn.client.stream.api.StreamApiService;
import com.porn.client.stream.dto.StreamQueryDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import com.porn.client.stream.vo.StreamVo;
import com.porn.service.mobile.api.ApiService;
import com.porn.service.mobile.converter.MobileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RefreshAccountInfoServiceImpl
        implements ApiService<LoginAccountVo> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private MobileConverter mobileConverter;

    @Autowired
    private StreamApiService streamApiService;

    @Autowired
    private AccountWalletService accountWalletService;


    public LoginAccountVo cmd(CmdRequestDTO cmdRequestDTO) {

        AccountVo accountVo = cmdRequestDTO.getAccountVo();


        AccountVo dbAccountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(accountVo.getId())).build());

        LoginAccountVo loginAccountVo = this.mobileConverter.toLoginAccountVo(dbAccountVo);

        loginAccountVo.setToken(cmdRequestDTO.getToken());


        if (ObjectUtil.isNotEmpty(dbAccountVo.getReceiveAddress())) {


            AccountWalletQueryDTO accountWalletQueryDTO = AccountWalletQueryDTO.builder().accountId(accountVo.getId()).address(dbAccountVo.getReceiveAddress()).build();

            AccountWalletVo accountWalletVo = this.accountWalletService.queryAccountWallet(accountWalletQueryDTO);

            if (ObjectUtil.isNotEmpty(accountWalletVo)) {

                loginAccountVo.setWalletCode(accountWalletVo.getWalletCode());

            }

        }


        StreamQueryDTO streamQueryDTO = StreamQueryDTO.builder().accountId(loginAccountVo.getId()).flag(StreamTypeEnum.REDENVELOPE.getFlag()).type(StreamTypeEnum.REDENVELOPE.getType()).bizId(Long.valueOf(-1L)).build();

        StreamVo streamVo = this.streamApiService.queryStream(streamQueryDTO);

        loginAccountVo.setReceiveRedPack(Integer.valueOf(ObjectUtil.isEmpty(streamVo) ? 0 : 1));

        return loginAccountVo;

    }


    public String getApi() {

        return "api_refreshaccount";

    }

}

