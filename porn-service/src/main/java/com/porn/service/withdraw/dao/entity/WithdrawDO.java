
package com.porn.service.withdraw.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("porn_withdraw")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class WithdrawDO extends BaseDO {
    @TableField("withdraw_no")
     private String withdrawNo;
    @TableField("account_id")
     private Long accountId;
    @TableField("account_name")
     private String accountName;
    @TableField("receive_address")
     private String receiveAddress;
    @TableField("total_amount")
     private BigDecimal totalAmount;
    @TableField("reality_amount")
     private BigDecimal realityAmount;
    
    @TableField("status_")
     private Integer status;
    
    @TableField("wallet_code")
     private String walletCode;
    
    @TableField("wallet_name")
     private String walletName;
    
    @TableField("account_remark")
     private String accountRemark;

}
