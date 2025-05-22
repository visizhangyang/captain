
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


