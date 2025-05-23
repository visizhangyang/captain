package com.porn.service.stream.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_stream")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StreamDO extends BaseDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("account_name")
    private String accountName;

    @TableField("before_total_balance")
    private BigDecimal beforeTotalBalance;

    @TableField("before_available_balance")
    private BigDecimal beforeAvailableBalance;

    @TableField("before_freeze_balance")
    private BigDecimal beforeFreezeBalance;

    @TableField("after_total_balance")
    private BigDecimal afterTotalBalance;

    @TableField("after_available_balance")
    private BigDecimal afterAvailableBalance;
    @TableField("after_freeze_balance")
    private BigDecimal afterFreezeBalance;
    @TableField("biz_id")
    private Long bizId;
    @TableField("_amount")
    private BigDecimal amount;
    @TableField("_type")
    private Integer type;
    @TableField("_flag")
    private Integer flag;

}
