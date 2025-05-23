package com.porn.service.mobile.api.impl;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.porn.client.common.enums.LangTypeEnum;
import com.porn.client.mobile.dto.CmdRequestDTO;
import com.porn.client.plan.api.PlanApiService;
import com.porn.client.plan.dto.PlanQueryDTO;
import com.porn.client.plan.vo.PlanVo;
import com.porn.service.mobile.api.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QueryPlanApiServiceImpl
        implements ApiService<List<PlanVo>> {
    private static final Logger log = LoggerFactory.getLogger(QueryPlanApiServiceImpl.class);

    @Autowired
    private PlanApiService planApiService;


    public List<PlanVo> cmd(CmdRequestDTO cmdRequestDTO) {

        PlanQueryDTO planQueryDTO = (PlanQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), PlanQueryDTO.class);

        if (ObjectUtil.isEmpty(planQueryDTO.getLangTypeName())) {

            planQueryDTO.setLangType(LangTypeEnum.ZH.getType());

        } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(planQueryDTO.getLangTypeName()))) {

            planQueryDTO.setLangType(LangTypeEnum.EN.getType());

        } else {

            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(planQueryDTO.getLangTypeName());

            planQueryDTO.setLangType(langTypeEnum.getType());

        }

        return this.planApiService.queryPlanList(planQueryDTO);

    }


    public String getApi() {

        return "api_queryplan";

    }

}

