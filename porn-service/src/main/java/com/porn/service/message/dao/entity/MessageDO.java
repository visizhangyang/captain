package com.porn.service.message.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MessageDO extends BaseDO {

    @TableField("msg_")
    private String msg;

    @TableField("account_id")
    private Long accountId;

    @TableField("account_name")
    private String accountName;


}
