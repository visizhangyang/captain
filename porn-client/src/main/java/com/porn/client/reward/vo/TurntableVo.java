
package com.porn.client.reward.vo;
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/vo/TurntableVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */