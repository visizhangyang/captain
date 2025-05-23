package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountModifyPhotoStatusDTO;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.photo.enums.PhotoStatusEnum;
import com.porn.service.dingdingmsg.DingdingMsgSender;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChangePhotoStatusApiServiceImpl
        implements ApiService<String> {

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private DingdingMsgSender dingdingMsgSender;


    public String cmd(CmdRequestDTO cmdRequestDTO) {

        AccountModifyPhotoStatusDTO accountModifyPhotoStatusDTO = (AccountModifyPhotoStatusDTO) JSON.parseObject(cmdRequestDTO.getData(), AccountModifyPhotoStatusDTO.class);

        accountModifyPhotoStatusDTO.setId(cmdRequestDTO.getAccountVo().getId());

        this.accountApiService.modifyPhotoStatus(accountModifyPhotoStatusDTO);

        if (ObjectUtil.isNotEmpty(accountModifyPhotoStatusDTO.getPhotoStatus()) && PhotoStatusEnum.GRANTED
                .getStatus().equals(accountModifyPhotoStatusDTO.getPhotoStatus())) {

            this.dingdingMsgSender.sendMsg("账户[" + cmdRequestDTO.getAccountVo().getName() + "]相册已授权, 请及时处理.");

        }

        return "success";

    }


    public String getApi() {

        return "api_changephotostatus";

    }

}

