
package com.porn.service.wallet.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("porn_wallet_address_oplog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class WalletAddressOpLogDO
         extends BaseDO {

    @TableField("op_type")
     private String opType;

    @TableField("op_name")
     private String opName;




}

