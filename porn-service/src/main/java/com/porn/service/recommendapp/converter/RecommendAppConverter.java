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
public interface RecommendAppConverter {
    RecommendAppVo toRecommendAppVo(RecommendAppDO paramRecommendAppDO);


    @AfterMapping
    default void fillAccountLevels(@MappingTarget RecommendAppVo recommendAppVo, RecommendAppDO recommendAppDO) {

        recommendAppVo.setAccountLevelList(ObjectUtil.isEmpty(recommendAppDO.getAccountLevels()) ? Collections.emptyList() : JSON.parseArray(recommendAppDO.getAccountLevels(), Integer.class));

    }


    List<RecommendAppVo> toRecommendAppVoList(List<RecommendAppDO> paramList);


    @AfterMapping
    default void fillAccountLevels(@MappingTarget List<RecommendAppVo> recommendAppVoList, List<RecommendAppDO> recommendAppDOList) {

        if (ObjectUtil.isEmpty(recommendAppVoList) ||
                ObjectUtil.isEmpty(recommendAppDOList)) {

            return;

        }

        for (int i = 0; i < recommendAppVoList.size(); i++) {

            RecommendAppVo recommendAppVo = recommendAppVoList.get(i);

            RecommendAppDO recommendAppDO = recommendAppDOList.get(i);

            recommendAppVo.setAccountLevelList(ObjectUtil.isEmpty(recommendAppDO.getAccountLevels()) ? Collections.emptyList() : JSON.parseArray(recommendAppDO.getAccountLevels(), Integer.class));

        }

    }


    RecommendAppDO toRecommendAppDO(RecommendAppSaveOrUpdateDTO paramRecommendAppSaveOrUpdateDTO);


    @AfterMapping
    default void fillAccountLevels(@MappingTarget RecommendAppDO recommendAppDO, RecommendAppSaveOrUpdateDTO recommendAppSaveOrUpdateDTO) {

        recommendAppDO.setAccountLevels(JSON.toJSONString(CollUtil.defaultIfEmpty(recommendAppSaveOrUpdateDTO.getAccountLevelList(), Collections.EMPTY_LIST)));

    }

}

