package com.porn.service.inline.converter;

import com.porn.client.inline.dto.InlineSaveOrUpdateDTO;
import com.porn.client.inline.vo.InlineVo;
import com.porn.service.inline.dao.entity.InlineDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InlineConverter {
    InlineVo toInlineVo(InlineDO paramInlineDO);

    List<InlineVo> toInlineVoList(List<InlineDO> paramList);

    InlineDO toInlineDO(InlineSaveOrUpdateDTO paramInlineSaveOrUpdateDTO);
}


