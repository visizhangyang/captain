package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateRechargeVo implements Serializable {
    @ApiModelProperty("充值主键")
    private Long id;
    @ApiModelProperty("充值流水号")
    private String rechargeNo;
    @ApiModelProperty("账户ID")
    private Long accountId;
    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("钱包ID")
    private Long walletId;
    @ApiModelProperty("钱包名称")
    private String walletName;
    @ApiModelProperty("钱包地址")
    private String walletAddress;
    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("状态, RechargeStatusEnum")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("签名")
    private String sign;

}

