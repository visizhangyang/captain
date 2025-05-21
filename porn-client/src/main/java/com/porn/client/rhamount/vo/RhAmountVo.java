
package com.porn.client.rhamount.vo;
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
 public class RhAmountVo
         extends BaseVo
         {

    @ApiModelProperty("金额")
     private BigDecimal amount;

    @ApiModelProperty("排序号")
     private Integer sortNo;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/rhamount/vo/RhAmountVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */