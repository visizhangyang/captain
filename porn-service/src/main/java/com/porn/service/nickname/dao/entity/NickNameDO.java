
package com.porn.service.nickname.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("porn_nickname")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class NickNameDO extends BaseDO {
    
    @TableField("nick_name")
     private String nickName;
    
    @TableField("nickname_type")
     private Integer nickNameType;
    
    @TableField("_status")
     private Integer status;

    
}
