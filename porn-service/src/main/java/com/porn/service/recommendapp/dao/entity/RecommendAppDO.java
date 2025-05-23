package com.porn.service.recommendapp.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_recommendapp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RecommendAppDO extends BaseDO {
    @TableField("avatar")
    private String avatar;
    @TableField("_code")
    private String code;
    @TableField("_name")
    private String name;
    @TableField("app_type")
    private String appType;
    @TableField("apk_url")
    private String apkUrl;
    @TableField("sort_no")
    private Integer sortNo;

    @TableField("copy_flag")
    private Integer copyFlag;

    @TableField("recommend_type")
    private Integer recommendType;

    @TableField("account_levels")
    private String accountLevels;

}

