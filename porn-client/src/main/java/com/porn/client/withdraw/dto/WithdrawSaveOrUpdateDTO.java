
package com.porn.client.withdraw.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/withdraw/dto/WithdrawSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */