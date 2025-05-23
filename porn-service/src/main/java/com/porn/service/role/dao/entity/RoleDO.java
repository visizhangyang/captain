package com.porn.service.role.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("sys_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoleDO extends BaseDO {

    @TableField("name")
    private String name;

    @TableField("status_")
    private Integer status;

    @TableField("description")
    private String description;


}

