package com.porn.service.workrobot.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_workrobot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WorkrobotDO extends BaseDO {
    @TableField("minwork_amount")
    private BigDecimal minWorkAmount;
    @TableField("maxwork_amount")
    private BigDecimal maxWorkAmount;
    @TableField("minwork_time")
    private String minWorkTime;
    @TableField("maxwork_time")
    private String maxWorkTime;
    @TableField("order_count")
    private Integer orderCount;

    @TableField("min_loantime")
    private Integer minLoanTime;

    @TableField("max_loantime")
    private Integer maxLoanTime;

    @TableField("min_completetime")
    private Integer minCompleteTime;

    @TableField("max_completetime")
    private Integer maxCompleteTime;

}
