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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/paramset/converter/ParamsetConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */