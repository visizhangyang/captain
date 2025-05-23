package com.porn.service.reward.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@TableName("porn_rewardbalance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RewardBalanceDO
        extends BaseDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("available_count")
    private BigDecimal availableCount;


}

