package com.porn.client.inline.api;

import com.porn.client.inline.dto.InlineQueryDTO;
import com.porn.client.inline.dto.InlineSaveOrUpdateDTO;
import com.porn.client.inline.vo.InlineVo;

import java.util.List;

public interface InlineApiService {
    InlineVo queryInline(InlineQueryDTO paramInlineQueryDTO);

    List<InlineVo> queryInlineList(InlineQueryDTO paramInlineQueryDTO);

    InlineVo saveOrUpdate(InlineSaveOrUpdateDTO paramInlineSaveOrUpdateDTO);

    Boolean batchCreate(List<InlineSaveOrUpdateDTO> paramList);
}

