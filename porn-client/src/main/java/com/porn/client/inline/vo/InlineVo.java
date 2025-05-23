package com.porn.client.inline.vo;

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
public class InlineVo extends BaseVo {

    @ApiModelProperty("最小在线人数")
    private Long minInlineCount;

    @ApiModelProperty("最大搬砖金额")
    private Long maxInlineCount;

    @ApiModelProperty("在线时间范围(小)")
    private String minInlineTime;

    @ApiModelProperty("在线时间范围(大)")
    private String maxInlineTime;

}

