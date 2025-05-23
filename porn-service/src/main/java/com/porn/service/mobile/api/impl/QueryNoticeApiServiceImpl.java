package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.common.vo.PageVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.notice.api.NoticeApiService;
import com.porn.client.notice.dto.NoticeQueryPageDTO;
import com.porn.client.notice.vo.NoticeVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class QueryNoticeApiServiceImpl
        implements ApiService<List<NoticeVo>> {

    @Autowired
    private NoticeApiService noticeApiService;


    public List<NoticeVo> cmd(CmdRequestDTO cmdRequestDTO) {

        NoticeQueryPageDTO noticeQueryPageDTO = (NoticeQueryPageDTO) JSON.parseObject(cmdRequestDTO.getData(), NoticeQueryPageDTO.class);

        if (ObjectUtil.isNotEmpty(noticeQueryPageDTO.getLangTypeName())) {

            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(noticeQueryPageDTO.getLangTypeName());

            if (ObjectUtil.isNotEmpty(langTypeEnum)) {

                noticeQueryPageDTO.setLangType(langTypeEnum.getType());

            }

        }

        PageVo<NoticeVo> pageVo = this.noticeApiService.queryPage(noticeQueryPageDTO);

        return (ObjectUtil.isEmpty(pageVo) || ObjectUtil.isEmpty(pageVo.getData())) ? Collections.<NoticeVo>emptyList() : pageVo.getData();

    }


    public String getApi() {

        return "api_querynotice";

    }

}

