package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.desc.api.DescApiService;
import com.porn.client.desc.dto.DescQueryDTO;
import com.porn.client.desc.vo.DescVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QueryDescApiServiceImpl
        implements ApiService<List<DescVo>> {

    @Autowired
    private DescApiService descApiService;


    public List<DescVo> cmd(CmdRequestDTO cmdRequestDTO) {

        DescQueryDTO descQueryDTO = (DescQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), DescQueryDTO.class);

        if (ObjectUtil.isEmpty(descQueryDTO.getLangTypeName())) {


            descQueryDTO.setLangType(LangTypeEnum.ZH.getType());

        } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(descQueryDTO.getLangTypeName()))) {


            descQueryDTO.setLangType(LangTypeEnum.EN.getType());

        } else {


            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(descQueryDTO.getLangTypeName());

            descQueryDTO.setLangType(langTypeEnum.getType());

        }

        List<DescVo> descVoList = this.descApiService.queryDescList(descQueryDTO);

        return (descVoList == null) ? Collections.<DescVo>emptyList() : descVoList;

    }


    public String getApi() {

        return "api_querydesclist";

    }

}

