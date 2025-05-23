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

