
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
         implements ApiService<List<DescVo>>
         {

    @Autowired
     private DescApiService descApiService;



    public List<DescVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 31 */
        DescQueryDTO descQueryDTO = (DescQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), DescQueryDTO.class);
        /* 32 */
        if (ObjectUtil.isEmpty(descQueryDTO.getLangTypeName())) {

            /* 34 */
            descQueryDTO.setLangType(LangTypeEnum.ZH.getType());
            /* 35 */
        } else if (ObjectUtil.isEmpty(LangTypeEnum.queryByTag(descQueryDTO.getLangTypeName()))) {

            /* 37 */
            descQueryDTO.setLangType(LangTypeEnum.EN.getType());

        } else {

            /* 40 */
            LangTypeEnum langTypeEnum = LangTypeEnum.queryByTag(descQueryDTO.getLangTypeName());
            /* 41 */
            descQueryDTO.setLangType(langTypeEnum.getType());

        }
        /* 43 */
        List<DescVo> descVoList = this.descApiService.queryDescList(descQueryDTO);
        /* 44 */
        return (descVoList == null) ? Collections.<DescVo>emptyList() : descVoList;

    }



    public String getApi() {
        /* 48 */
        return "api_querydesclist";

    }

}


