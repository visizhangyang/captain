package com.porn.service.inline.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_inline")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InlineDO extends BaseDO {

    @TableField("mininline_count")
    private Long minInlineCount;

    @TableField("maxinline_count")
    private Long maxInlineCount;

    @TableField("mininline_time")
    private String minInlineTime;

    @TableField("maxinline_time")
    private String maxInlineTime;
}
