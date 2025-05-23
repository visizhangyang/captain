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
public class AccountTreasureVo
        implements Serializable {

    @ApiModelProperty("今日收益")
    private BigDecimal todayReceive;

    @ApiModelProperty("总收益")
    private BigDecimal totalReceive;

}
