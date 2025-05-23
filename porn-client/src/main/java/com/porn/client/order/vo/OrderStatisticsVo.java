package com.porn.client.order.vo;

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
public class OrderStatisticsVo
        implements Serializable {

    @ApiModelProperty("总账订单数量")
    private Long totalOrderCount;

    @ApiModelProperty("总账订单金额")
    private BigDecimal totalOrderAmount;

}

