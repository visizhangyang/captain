
package com.porn.client.reward.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class RewardBalanceLuckVo
         extends RewardBalanceVo
         {

    @ApiModelProperty("真实中奖序号")
     private Integer luckIndex;



}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/vo/RewardBalanceLuckVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */