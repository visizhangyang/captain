
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;





@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class OrderAppNotifyVo
         implements Serializable
         {

    @ApiModelProperty("订单ID")
     private Long orderId;




}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/OrderAppNotifyVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */