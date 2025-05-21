
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-service-3.3.0.jar!/com/porn/service/account/dao/entity/AccountExtDO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */