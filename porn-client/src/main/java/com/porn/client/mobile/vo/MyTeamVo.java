package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MyTeamVo implements Serializable {

    @ApiModelProperty("总数量")
    private Integer totalCount;

    @ApiModelProperty("一级数量")
    private Integer level1Count;

    @ApiModelProperty("二级数量")
    private Integer level2Count;

    @ApiModelProperty("三级数量")
    private Integer level3Count;

}

