package com.porn.client.richtext.api;

import com.porn.client.common.entity.Pair;
import com.porn.client.richtext.dto.RichTextQueryDTO;
import com.porn.client.richtext.dto.RichTextSaveOrUpdateDTO;
import com.porn.client.richtext.vo.RichTextVo;

import java.util.List;

public interface RichTextApiService {
    RichTextVo queryRichText(RichTextQueryDTO paramRichTextQueryDTO);

    List<RichTextVo> queryRichTextList(RichTextQueryDTO paramRichTextQueryDTO);

    RichTextVo saveOrUpdate(RichTextSaveOrUpdateDTO paramRichTextSaveOrUpdateDTO);

    List<Pair> queryTypes();
}

