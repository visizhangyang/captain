package com.porn.client.plan.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PlanInsExtVo extends PlanInsVo {

    @ApiModelProperty("是否存在计划, 0-不存在, 1-存在")
    private Integer existsPlan;

    @ApiModelProperty("当前进度")
    private Long curProgress;

    @ApiModelProperty("总进度")
    private Long totalProgress;

    @ApiModelProperty("计划标题")
    private String planTitle;

    @ApiModelProperty("范围值(小)")
    private BigDecimal minRange;

    @ApiModelProperty("范围值(大)")
    private BigDecimal maxRange;

}

