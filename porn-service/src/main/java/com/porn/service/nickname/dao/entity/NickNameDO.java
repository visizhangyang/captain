package com.porn.service.nickname.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
