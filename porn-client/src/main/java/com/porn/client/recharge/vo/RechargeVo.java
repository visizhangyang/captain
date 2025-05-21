
package com.porn.client.recharge.vo;
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/vo/RechargeVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */