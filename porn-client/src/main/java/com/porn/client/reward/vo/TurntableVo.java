package com.porn.client.reward.vo;

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
public class TurntableVo extends BaseVo {
    @ApiModelProperty("数字0")
    private BigDecimal num0;
    @ApiModelProperty("数字1")
    private BigDecimal num1;
    @ApiModelProperty("数字2")
    private BigDecimal num2;

    @ApiModelProperty("数字3")
    private BigDecimal num3;

    @ApiModelProperty("数字4")
    private BigDecimal num4;

    @ApiModelProperty("数字5")
    private BigDecimal num5;

    @ApiModelProperty("数字6")
    private BigDecimal num6;

    @ApiModelProperty("数字7")
    private BigDecimal num7;

}

