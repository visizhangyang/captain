package com.porn.service.rhamount.converter;

import com.porn.client.rhamount.dto.RhAmountSaveOrUpdateDTO;
import com.porn.client.rhamount.vo.RhAmountVo;
import com.porn.service.rhamount.dao.entity.RhAmountDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RhAmountConverter {
    RhAmountVo toRhAmountVo(RhAmountDO paramRhAmountDO);

    List<RhAmountVo> toRhAmountVoList(List<RhAmountDO> paramList);

    RhAmountDO toRhAmountDO(RhAmountSaveOrUpdateDTO paramRhAmountSaveOrUpdateDTO);
}

