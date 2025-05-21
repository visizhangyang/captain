
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
 public class RewardBalanceVo
         extends BaseVo
         {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("可用次数")
     private BigDecimal availableCount;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/vo/RewardBalanceVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */