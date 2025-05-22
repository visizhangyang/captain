package com.porn.client.paramset.api;

import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.dto.ParamsetSaveOrUpdateDTO;
import com.porn.client.paramset.vo.ParamsetVo;

public interface ParamsetApiService {
    ParamsetVo queryParamset(ParamsetQueryDTO paramParamsetQueryDTO);

    ParamsetVo saveOrUpdate(ParamsetSaveOrUpdateDTO paramParamsetSaveOrUpdateDTO);
}


