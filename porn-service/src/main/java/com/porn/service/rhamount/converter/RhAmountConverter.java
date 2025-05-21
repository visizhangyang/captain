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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/rhamount/converter/RhAmountConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */