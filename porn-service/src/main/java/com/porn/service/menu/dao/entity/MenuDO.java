package com.porn.service.menu.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("sys_menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MenuDO extends BaseDO {
    @TableField("name")
    private String name;
    @TableField("icon_type")
    private Integer iconType;

    @TableField("icon_path")
    private String iconPath;

    @TableField("url_path")
    private String urlPath;

    @TableField("description")
    private String description;

    @TableField("sort_no")
    private Integer sortNo;

    @TableField("parent_id")
    private Long parentId;

}

