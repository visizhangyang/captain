package com.porn.client.richtext.vo;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RichTextVo extends BaseVo {

    @ApiModelProperty("类型, RichTextTypeEnum")
    private Integer type;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("大字符串")
    private String richText;

}

