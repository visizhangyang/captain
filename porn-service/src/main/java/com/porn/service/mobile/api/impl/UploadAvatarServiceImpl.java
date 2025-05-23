package com.porn.service.mobile.api.impl;


import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountSaveOrUpdateDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.mobile.dto.UploadAvatarRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UploadAvatarServiceImpl
        implements ApiService<AccountVo> {

    @Autowired
    private AccountApiService accountApiService;


    public AccountVo cmd(CmdRequestDTO cmdRequestDTO) {

        UploadAvatarRequestDTO uploadAvatarRequestDTO = (UploadAvatarRequestDTO) JSON.parseObject(cmdRequestDTO.getData(), UploadAvatarRequestDTO.class);


        AccountSaveOrUpdateDTO accountSaveOrUpdateDTO = ((AccountSaveOrUpdateDTO.AccountSaveOrUpdateDTOBuilder) AccountSaveOrUpdateDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).avatar(uploadAvatarRequestDTO.getAvatarPath()).build();

        return this.accountApiService.saveOrUpdate(accountSaveOrUpdateDTO);

    }


    public String getApi() {

        return "api_uploadavatar";

    }

}

