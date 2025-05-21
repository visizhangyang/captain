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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/inline/converter/InlineConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */