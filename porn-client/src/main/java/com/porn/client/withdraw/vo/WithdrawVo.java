package com.porn.client.withdraw.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WithdrawVo extends BaseVo {
    @ApiModelProperty("流水号")
    private String withdrawNo;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("收款地址")
    private String receiveAddress;

    @ApiModelProperty("提现金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("到账金额")
    private BigDecimal realityAmount;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private Integer status;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("钱包名称")
    private String walletName;

    @ApiModelProperty("账户备注")
    private String accountRemark;

}