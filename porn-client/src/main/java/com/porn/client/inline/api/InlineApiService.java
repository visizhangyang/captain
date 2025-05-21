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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/inline/api/InlineApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */