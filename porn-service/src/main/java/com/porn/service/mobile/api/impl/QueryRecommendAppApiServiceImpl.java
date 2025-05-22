
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
         implements ApiService<List<RecommendAppVo>>
         {
    
    @Autowired
     private RecommendAppService recommendAppService;

    
    
    public List<RecommendAppVo> cmd(CmdRequestDTO cmdRequestDTO) {
        /* 31 */
        RecommendAppQueryDTO recommendAppQueryDTO = (RecommendAppQueryDTO) JSON.parseObject(cmdRequestDTO.getData(), RecommendAppQueryDTO.class);
        /* 32 */
        if (ObjectUtil.isEmpty(recommendAppQueryDTO.getRecommendType())) {
            /* 33 */
            recommendAppQueryDTO.setRecommendType(RecommendTypeEnum.DECRECOMMEND.getType());
            
        }
        /* 35 */
        return this.recommendAppService.queryRecommendAppList(recommendAppQueryDTO);
        
    }

    
    
    public String getApi() {
        /* 39 */
        return "api_queryrecommendapp";
        
    }
    
}


