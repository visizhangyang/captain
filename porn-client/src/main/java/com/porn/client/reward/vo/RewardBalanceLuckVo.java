package com.porn.client.reward.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RewardBalanceLuckVo
        extends RewardBalanceVo {

    @ApiModelProperty("真实中奖序号")
    private Integer luckIndex;

}

