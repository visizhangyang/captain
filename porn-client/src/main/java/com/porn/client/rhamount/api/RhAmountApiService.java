package com.porn.client.rhamount.api;

import com.porn.client.rhamount.dto.RhAmountQueryDTO;
import com.porn.client.rhamount.dto.RhAmountSaveOrUpdateDTO;
import com.porn.client.rhamount.vo.RhAmountVo;

import java.util.List;

public interface RhAmountApiService {
    RhAmountVo queryRhAmount(RhAmountQueryDTO paramRhAmountQueryDTO);

    List<RhAmountVo> queryRhAmountList(RhAmountQueryDTO paramRhAmountQueryDTO);

    RhAmountVo saveOrUpdate(RhAmountSaveOrUpdateDTO paramRhAmountSaveOrUpdateDTO);

    Boolean batchCreate(List<RhAmountSaveOrUpdateDTO> paramList);
}
