package com.porn.service.desc.converter;

import com.porn.client.desc.dto.DescSaveOrUpdateDTO;
import com.porn.client.desc.vo.DescVo;
import com.porn.service.desc.dao.entity.DescDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DescConverter {
    DescVo toDescVo(DescDO paramDescDO);

    List<DescVo> toDescVoList(List<DescDO> paramList);

    DescDO toDescDO(DescSaveOrUpdateDTO paramDescSaveOrUpdateDTO);
}

