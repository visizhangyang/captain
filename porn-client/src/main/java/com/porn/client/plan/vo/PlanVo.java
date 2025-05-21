
package com.porn.client.plan.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PlanVo extends BaseVo {
    @ApiModelProperty("标题")
     private String title;
    @ApiModelProperty("范围值(小)")
     private BigDecimal minRange;
    @ApiModelProperty("范围值(大)")
     private BigDecimal maxRange;
    @ApiModelProperty("费用")
     private BigDecimal free;
    @ApiModelProperty("描述")
     private String desc;
    @ApiModelProperty("预计收益, %")
     private BigDecimal revenue;
    @ApiModelProperty("天数")
     private Integer days;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("排序号")
     private Integer sortNo;

    @ApiModelProperty("额外加成")
     private BigDecimal extraBonus;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/vo/PlanVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */