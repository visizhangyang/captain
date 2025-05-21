
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
 public class PlatformStatisticsVo implements Serializable {

    @ApiModelProperty("总账户数量")
     private Long totalAccountCount;

    @ApiModelProperty("今天新增数量")
     private Long todayAccountCount;

    @ApiModelProperty("总账订单数量")
     private Long totalOrderCount;

    @ApiModelProperty("总账订单金额")
     private BigDecimal totalOrderAmount;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/PlatformStatisticsVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */