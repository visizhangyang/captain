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
public class RewardBalanceVo
        extends BaseVo {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("可用次数")
    private BigDecimal availableCount;

}

