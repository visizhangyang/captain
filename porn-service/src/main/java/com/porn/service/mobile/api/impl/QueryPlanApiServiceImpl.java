
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
         implements ApiService<List<PlanVo>>
         {
    /* 22 */   private static final Logger log = LoggerFactory.getLogger(QueryPlanApiServiceImpl.class);




    @Autowired
     private PlanApiService planApiService;





    public List<PlanVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 32 */
        PlanQueryDTO planQueryDTO = (PlanQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), PlanQueryDTO.class);
        /* 33 */
        if (ObjectUtil.isEmpty(planQueryDTO.getLangTypeName())) {
            /* 34 */
            planQueryDTO.setLangType(LangTypeEnum.ZH.getType());
            /* 35 */
        } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(planQueryDTO.getLangTypeName()))) {

            /* 37 */
            planQueryDTO.setLangType(LangTypeEnum.EN.getType());

        } else {

            /* 40 */
            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(planQueryDTO.getLangTypeName());
            /* 41 */
            planQueryDTO.setLangType(langTypeEnum.getType());

        }
        /* 43 */
        return this.planApiService.queryPlanList(planQueryDTO);

    }



    public String getApi() {
        /* 47 */
        return "api_queryplan";

    }

}


