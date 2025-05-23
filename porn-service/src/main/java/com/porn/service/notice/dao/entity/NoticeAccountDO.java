package com.porn.service.notice.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_notice_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NoticeAccountDO
        extends BaseDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("notice_id")
    private Long noticeId;


}

