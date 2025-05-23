package com.porn.service.reward.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_rewardrecord")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RewardRecordDO extends BaseDO {
    @TableField("account_id")
    private Long accountId;

    @TableField("before_availablecount")
    private BigDecimal beforeAvailableCount;

    @TableField("after_availablecount")
    private BigDecimal afterAvailableCount;

    @TableField("operate_count")
    private BigDecimal operateCount;

    @TableField("biz_type")
    private Integer bizType;

    @TableField("biz_id")
    private String bizId;

    @TableField("_type")
    private Integer type;

}

