package com.porn.client.recharge.vo;

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
public class RechargeVo extends BaseVo {
    @ApiModelProperty("充值流水号")
    private String rechargeNo;
    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("来源地址")
    private String fromAddress;

    @ApiModelProperty("交易hash")
    private String hash;

    @ApiModelProperty("钱包ID")
    private Long walletId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("钱包名称")
    private String walletName;
    @ApiModelProperty("钱包地址")
    private String walletAddress;
    @ApiModelProperty("金额")
    private BigDecimal amount;
    @ApiModelProperty("手续费")
    private BigDecimal gasAmount;
    @ApiModelProperty("到账金额")
    private BigDecimal receiveAmount;
    @ApiModelProperty("状态, RechargeStatusEnum")
    private Integer status;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("钱包备注")
    private String walletRemark;

}

