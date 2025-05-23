package com.porn.client.workrobot.vo;

import com.porn.client.common.vo.BaseVo;
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
public class WorkrobotVo extends BaseVo {
    @ApiModelProperty("最小搬砖金额")
    private BigDecimal minWorkAmount;
    @ApiModelProperty("最大搬砖金额")
    private BigDecimal maxWorkAmount;
    @ApiModelProperty("搬砖时间范围(小)")
    private String minWorkTime;
    @ApiModelProperty("搬砖时间范围(大)")
    private String maxWorkTime;
    @ApiModelProperty("订单数量")
    private Integer orderCount;

    @ApiModelProperty("放款时间(小)")
    private Integer minLoanTime;

    @ApiModelProperty("放款时间(大)")
    private Integer maxLoanTime;

    @ApiModelProperty("完成时间(小)")
    private Integer minCompleteTime;

    @ApiModelProperty("完成时间(大)")
    private Integer maxCompleteTime;

}

