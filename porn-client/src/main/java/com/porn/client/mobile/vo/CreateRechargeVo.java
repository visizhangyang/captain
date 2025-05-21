
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/CreateRechargeVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */