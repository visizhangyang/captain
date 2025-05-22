
package com.porn.client.autowork.vo;
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
 public class AutoWorkVo extends BaseVo {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("最小搬砖次数")
     private Integer minWorkCount;

    @ApiModelProperty("最大搬砖次数")
     private Integer maxWorkCount;

    @ApiModelProperty("最小搬砖金额")
     private BigDecimal minWorkAmount;

    @ApiModelProperty("最大搬砖金额")
     private BigDecimal maxWorkAmount;

    @ApiModelProperty("最小搬砖间隔(分钟)")
     private Integer minWorkSpace;

    @ApiModelProperty("最大搬砖间隔(分钟)")
    private Integer maxWorkSpace;
    @ApiModelProperty("搬砖开始时间")
    private LocalDateTime minWorkTime;
    @ApiModelProperty("搬砖结束时间")
    private LocalDateTime maxWorkTime;
    @ApiModelProperty("放款时间(小)")
    private Integer minLoanTime;
    @ApiModelProperty("放款时间(大)")
    private Integer maxLoanTime;
    @ApiModelProperty("完成时间(小)")
    private Integer minCompleteTime;
    @ApiModelProperty("完成时间(大)")
    private Integer maxCompleteTime;


}


