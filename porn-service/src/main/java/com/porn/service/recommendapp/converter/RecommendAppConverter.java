
package com.porn.service.recommendapp.converter;



import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.porn.client.recommendapp.dto.RecommendAppSaveOrUpdateDTO;
import com.porn.client.recommendapp.vo.RecommendAppVo;
import com.porn.service.recommendapp.dao.entity.RecommendAppDO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;
























@Mapper(componentModel = "spring")
 public interface RecommendAppConverter
         {
       RecommendAppVo toRecommendAppVo(RecommendAppDO paramRecommendAppDO);

    
    
    @AfterMapping
     default void fillAccountLevels(@MappingTarget RecommendAppVo recommendAppVo, RecommendAppDO recommendAppDO) {
        /* 32 */
        recommendAppVo.setAccountLevelList(ObjectUtil.isEmpty(recommendAppDO.getAccountLevels()) ? Collections.emptyList() : JSON.parseArray(recommendAppDO.getAccountLevels(), Integer.class));
        
    }

    
    
    
       List<RecommendAppVo> toRecommendAppVoList(List<RecommendAppDO> paramList);

    
    
    
    
    @AfterMapping
     default void fillAccountLevels(@MappingTarget List<RecommendAppVo> recommendAppVoList, List<RecommendAppDO> recommendAppDOList) {
        /* 43 */
        if (ObjectUtil.isEmpty(recommendAppVoList) ||
                /* 44 */       ObjectUtil.isEmpty(recommendAppDOList)) {
            
            return;
            
        }
        /* 47 */
        for (int i = 0; i < recommendAppVoList.size(); i++) {
            /* 48 */
            RecommendAppVo recommendAppVo = recommendAppVoList.get(i);
            /* 49 */
            RecommendAppDO recommendAppDO = recommendAppDOList.get(i);
            /* 50 */
            recommendAppVo.setAccountLevelList(ObjectUtil.isEmpty(recommendAppDO.getAccountLevels()) ? Collections.emptyList() : JSON.parseArray(recommendAppDO.getAccountLevels(), Integer.class));
            
        }
        
    }

    
    
    
       RecommendAppDO toRecommendAppDO(RecommendAppSaveOrUpdateDTO paramRecommendAppSaveOrUpdateDTO);

    
    
    
    
    @AfterMapping
     default void fillAccountLevels(@MappingTarget RecommendAppDO recommendAppDO, RecommendAppSaveOrUpdateDTO recommendAppSaveOrUpdateDTO) {
        /* 62 */
        recommendAppDO.setAccountLevels(JSON.toJSONString(CollUtil.defaultIfEmpty(recommendAppSaveOrUpdateDTO.getAccountLevelList(), Collections.EMPTY_LIST)));
        
    }
    
}


