
package com.porn.client.plan.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PlanInsVo extends BaseVo {
    @ApiModelProperty("账户ID")
     private Long accountId;
    @ApiModelProperty("计划ID")
     private Long planId;
    @ApiModelProperty("昨日收益")
     private BigDecimal yesterdayProfit;
    @ApiModelProperty("总收益")
     private BigDecimal totalProfit;

    @ApiModelProperty("总投入")
     private BigDecimal totalInvest;

    @ApiModelProperty("开始时间")
     private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
     private LocalDateTime endTime;

    @ApiModelProperty("状态, PlanInsStatusEnum")
     private Integer status;

    @ApiModelProperty("额外加成")
     private BigDecimal extraBonus;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/vo/PlanInsVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */