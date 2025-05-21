
package com.porn.service.user.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("sys_user_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class UserRoleDO
         extends BaseDO {

    @TableField("user_id")
     private Long userId;

    @TableField("role_id")
     private Long roleId;




}

