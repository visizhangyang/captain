package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

