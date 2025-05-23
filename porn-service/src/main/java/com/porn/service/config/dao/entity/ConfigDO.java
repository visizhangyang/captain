package com.porn.service.config.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ConfigDO extends BaseDO {
    @TableField("config_code")
    private String configCode;
    @TableField("config_group")
    private String configGroup;

    @TableField("config_value")
    private String configValue;

    @TableField("config_desc")
    private String configDesc;

    @TableField("_status")
    private Integer status;

    @TableField("sort_no")
    private Integer sortNo;

    @TableField("account_id")
    private Long accountId;

}
