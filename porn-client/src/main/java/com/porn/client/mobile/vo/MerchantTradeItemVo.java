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
public class MerchantTradeItemVo implements Serializable {
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("账户id")
    private Long accountId;
    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("账户头像")
    private String accountAvatar;
    @ApiModelProperty("账户头像")
    private String accountAvatarUrl;
    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty("订单费率(%)")
    private BigDecimal orderRate;
    @ApiModelProperty("订单佣金")
    private BigDecimal freeAmount;

}

