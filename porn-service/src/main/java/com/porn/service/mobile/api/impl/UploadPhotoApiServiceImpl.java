package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.account.api.AccountApiService;
import com.porn.client.account.dto.AccountQueryDTO;
import com.porn.client.account.vo.AccountVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.photo.api.PhotoApiService;
import com.porn.client.photo.dto.PhotoQueryDTO;
import com.porn.client.photo.dto.PhotoSaveOrUpdateDTO;
import com.porn.client.photo.vo.PhotoVo;
import com.porn.client.photo.vo.UploadPhotoVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadPhotoApiServiceImpl
        implements ApiService<UploadPhotoVo> {

    @Autowired
    private PhotoApiService photoApiService;

    @Autowired
    private AccountApiService accountApiService;


    public UploadPhotoVo cmd(CmdRequestDTO cmdRequestDTO) {

        AccountVo accountVo = this.accountApiService.queryAccount(((AccountQueryDTO.AccountQueryDTOBuilder) AccountQueryDTO.builder().id(cmdRequestDTO.getAccountVo().getId())).build());

        PhotoSaveOrUpdateDTO photoSaveOrUpdateDTO = (PhotoSaveOrUpdateDTO) JSON.parseObject(cmdRequestDTO.getData(), PhotoSaveOrUpdateDTO.class);

        if (ObjectUtil.isNotEmpty(photoSaveOrUpdateDTO.getLocalIdentifier())) {

            PhotoVo photoVo = this.photoApiService.queryPhoto(PhotoQueryDTO.builder().accountId(cmdRequestDTO.getAccountVo().getId()).localIdentifier(photoSaveOrUpdateDTO.getLocalIdentifier()).build());

            if (ObjectUtil.isNotEmpty(photoVo)) {

                return UploadPhotoVo.builder().uploadStatus(accountVo.getUploadStatus()).build();

            }

        }

        photoSaveOrUpdateDTO.setAccountId(cmdRequestDTO.getAccountVo().getId());

        photoSaveOrUpdateDTO.setAccountName(cmdRequestDTO.getAccountVo().getName());

        this.photoApiService.saveOrUpdate(photoSaveOrUpdateDTO);

        return UploadPhotoVo.builder().uploadStatus(accountVo.getUploadStatus()).build();

    }


    public String getApi() {

        return "api_uploadphoto";

    }

}

