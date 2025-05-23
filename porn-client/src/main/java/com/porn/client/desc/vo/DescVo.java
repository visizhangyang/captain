package com.porn.client.desc.vo;

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
public class DescVo extends BaseVo {

    @ApiModelProperty("描述类型, DescTypeEnum")
    private Integer descType;

    @ApiModelProperty("描述文本")
    private String descText;

    @ApiModelProperty("排序号")
    private Integer sortNo;

}


