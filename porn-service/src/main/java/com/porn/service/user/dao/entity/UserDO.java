package com.porn.service.user.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDO extends BaseDO {
    @TableField("name")
    private String name;

    @TableField("nick_name")
    private String nickName;

    @TableField("avatar")
    private String avatar;

    @TableField("pwd")
    private String pwd;

    @TableField("sign")
    private String sign;

    @TableField("status_")
    private Integer status;

}

