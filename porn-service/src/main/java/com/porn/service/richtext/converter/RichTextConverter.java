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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/richtext/converter/RichTextConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */