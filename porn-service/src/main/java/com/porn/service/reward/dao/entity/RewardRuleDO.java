package com.porn.service.reward.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_rewardrule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RewardRuleDO extends BaseDO {
    @TableField("name")
    private String name;

    @TableField("sub_name")
    private String subName;

    @TableField("rule_type")
    private Integer ruleType;

    @TableField("lang_type")
    private Integer langType;

    @TableField("rule_img")
    private String ruleImg;

    @TableField("total_amount")
    private BigDecimal totalAmount;

    @TableField("reward_num")
    private Integer rewardNum;

}
