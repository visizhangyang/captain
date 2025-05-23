package com.porn.client.withdraw.dto;

import com.porn.client.common.dto.BaseDTO;
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
public class WithdrawSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("流水号")
    private String withdrawNo;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("收款地址")
    private String receiveAddress;

    @ApiModelProperty("提现金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("到账金额")
    private BigDecimal realityAmount;

    @ApiModelProperty("交易密码")
    private String tradePwd;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private Integer status;

    @ApiModelProperty("账户备注")
    private String accountRemark;

}

