
package com.porn.client.plan.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/vo/PlanInsExtVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */