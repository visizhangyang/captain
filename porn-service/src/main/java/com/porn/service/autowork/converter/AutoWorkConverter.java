package com.porn.service.autowork.converter;

import com.porn.client.autowork.dto.AutoWorkSaveOrUpdateDTO;
import com.porn.client.autowork.vo.AutoWorkVo;
import com.porn.service.autowork.dao.entity.AutoWorkDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoWorkConverter {
    AutoWorkVo toAutoWorkVo(AutoWorkDO paramAutoWorkDO);

    List<AutoWorkVo> toAutoWorkList(List<AutoWorkDO> paramList);

    AutoWorkDO toAutoWorkDO(AutoWorkSaveOrUpdateDTO paramAutoWorkSaveOrUpdateDTO);
}

