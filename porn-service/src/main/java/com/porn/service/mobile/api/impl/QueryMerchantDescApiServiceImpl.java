package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.merchant.api.MerchantDescApiService;
import com.porn.client.merchant.dto.MerchantDescQueryDTO;
import com.porn.client.merchant.vo.MerchantDescVo;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryMerchantDescApiServiceImpl
        implements ApiService<MerchantDescVo> {

    @Autowired
    private MerchantDescApiService merchantDescApiService;


    public MerchantDescVo cmd(CmdRequestDTO cmdRequestDTO) {

        MerchantDescQueryDTO merchantDescQueryDTO = (MerchantDescQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), MerchantDescQueryDTO.class);

        LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(merchantDescQueryDTO.getLangTypeName());

        merchantDescQueryDTO.setLangType(ObjectUtil.isEmpty(langTypeEnum) ? LangTypeEnum.ZH.getType() : langTypeEnum.getType());

        return this.merchantDescApiService.queryMerchantDesc(merchantDescQueryDTO);

    }


    public String getApi() {

        return "api_querymerchantdesc";

    }

}

