package com.porn.service.richtext.converter;

import com.porn.client.richtext.dto.RichTextSaveOrUpdateDTO;
import com.porn.client.richtext.vo.RichTextVo;
import com.porn.service.richtext.dao.entity.RichTextDO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RichTextConverter {
    RichTextVo toRichTextVo(RichTextDO paramRichTextDO);

    List<RichTextVo> toRichTextVoList(List<RichTextDO> paramList);

    RichTextDO toRichTextDO(RichTextSaveOrUpdateDTO paramRichTextSaveOrUpdateDTO);
}

