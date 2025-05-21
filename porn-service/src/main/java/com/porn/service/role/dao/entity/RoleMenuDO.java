
package com.porn.service.role.dao.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;


@TableName("sys_role_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class RoleMenuDO
         extends BaseDO {

    @TableField("role_id")
     private Long roleId;

    @TableField("menu_id")
     private Long menuId;



}

