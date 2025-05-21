
package com.porn.service.transfer.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("porn_transfer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class TransferDO extends BaseDO {
    @TableField("srcccount_id")
     private Long srcAccountId;
    @TableField("srcaccount_name")
     private String srcAccountName;

    @TableField("dstccount_id")
     private Long dstAccountId;

    @TableField("dstaccount_name")
     private String dstAccountName;

    @TableField("_amount")
     private BigDecimal amount;

    @TableField("transfer_status")
     private Integer transferStatus;

    @TableField("src_account_remark")
     private String srcAccountRemark;

    @TableField("dst_account_remark")
     private String dstAccountRemark;


}

