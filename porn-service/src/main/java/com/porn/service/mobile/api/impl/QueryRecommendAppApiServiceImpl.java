package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.recommendapp.api.RecommendAppService;
import com.porn.client.recommendapp.dto.RecommendAppQueryDTO;
import com.porn.client.recommendapp.enums.RecommendTypeEnum;
import com.porn.client.recommendapp.vo.RecommendAppVo;
import com.porn.service.mobile.api.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryRecommendAppApiServiceImpl
        implements ApiService<List<RecommendAppVo>> {

    @Autowired
    private RecommendAppService recommendAppService;


    public List<RecommendAppVo> cmd(CmdRequestDTO cmdRequestDTO) {

        RecommendAppQueryDTO recommendAppQueryDTO = (RecommendAppQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), RecommendAppQueryDTO.class);

        if (ObjectUtil.isEmpty(recommendAppQueryDTO.getRecommendType())) {

            recommendAppQueryDTO.setRecommendType(RecommendTypeEnum.DECRECOMMEND.getType());

        }

        return this.recommendAppService.queryRecommendAppList(recommendAppQueryDTO);

    }


    public String getApi() {

        return "api_queryrecommendapp";

    }

}

