package com.porn.client.paramset.api;

import com.porn.client.paramset.dto.ParamsetQueryDTO;
import com.porn.client.paramset.dto.ParamsetSaveOrUpdateDTO;
import com.porn.client.paramset.vo.ParamsetVo;

public interface ParamsetApiService {
    ParamsetVo queryParamset(ParamsetQueryDTO paramParamsetQueryDTO);

    ParamsetVo saveOrUpdate(ParamsetSaveOrUpdateDTO paramParamsetSaveOrUpdateDTO);
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/paramset/api/ParamsetApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */