package com.porn.service.paramset.converter;

import com.porn.client.paramset.dto.ParamsetSaveOrUpdateDTO;
import com.porn.client.paramset.vo.ParamsetVo;
import com.porn.service.paramset.dao.entity.ParamsetDO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParamsetConverter {
    ParamsetVo toParamsetVo(ParamsetDO paramParamsetDO);

    ParamsetDO toParamsetDO(ParamsetSaveOrUpdateDTO paramParamsetSaveOrUpdateDTO);
}


