
package com.porn.service.log.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("porn_loginlog")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class LoginLogDO extends BaseDO {
    
    @TableField("user_id")
     private Long userId;
    
    @TableField("name")
     private String name;
    
    @TableField("login_ip")
     private String loginIp;

    

    
}

