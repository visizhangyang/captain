
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;





@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class AssetsStatisticsVo implements Serializable {

    @ApiModelProperty("累计订单数")
     private Long totalOrderCount;

    @ApiModelProperty("累计订单金额")
     private BigDecimal totalOrderAmount;

    @ApiModelProperty("累计订单佣金")
     private BigDecimal totalOrderFree;

    @ApiModelProperty("累计团队收益")
     private BigDecimal totalTeamAmount;



}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/AssetsStatisticsVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */