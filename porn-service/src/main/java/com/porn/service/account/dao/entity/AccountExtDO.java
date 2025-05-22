
package com.porn.service.account.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.*;import lombok.experimental.*;

@TableName("porn_account_ext")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
 public class AccountExtDO extends BaseDO {
    @TableField("account_id")
     private Long accountId;
    @TableField("ext_type")
     private Integer extType;
    
    @TableField("ext_key")
     private String extKey;
    
    @TableField("ext_value")
     private String extValue;
    
    @TableField("attr1")
     private String attr1;
    
    @TableField("attr2")
     private String attr2;
    
    @TableField("attr3")
     private String attr3;


}


