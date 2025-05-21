
package com.porn.client.transfer.vo;
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
public class TransferVo extends BaseVo {
    @ApiModelProperty("发起转账的ID")
     private Long srcAccountId;
    @ApiModelProperty("发起转账的名称")
     private String srcAccountName;
    @ApiModelProperty("发起转账的头像")
     private String srcAccountAvatarUrl;
    @ApiModelProperty("目标转账的ID")
     private Long dstAccountId;
    @ApiModelProperty("目标转账的名称")
     private String dstAccountName;
    @ApiModelProperty("接收转账的头像")
     private String dstAccountAvatarUrl;
    @ApiModelProperty("金额")
     private BigDecimal amount;

    @ApiModelProperty("转账状态, TransferStatusEnum")
     private Integer transferStatus;

    @ApiModelProperty("源账户备注")
     private String srcAccountRemark;

    @ApiModelProperty("目标账户备注")
     private String dstAccountRemark;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/transfer/vo/TransferVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */