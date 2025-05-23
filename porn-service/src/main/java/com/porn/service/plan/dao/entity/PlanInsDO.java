package com.porn.service.plan.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("porn_plan_ins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PlanInsDO extends BaseDO {
    @TableField("account_id")
    private Long accountId;
    @TableField("plan_id")
    private Long planId;
    @TableField("yesterday_profit")
    private BigDecimal yesterdayProfit;
    @TableField("total_profit")
    private BigDecimal totalProfit;

    @TableField("total_invest")
    private BigDecimal totalInvest;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField("_status")
    private Integer status;

    @TableField("extra_bonus")
    private BigDecimal extraBonus;

}

