package com.porn.service.recharge.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_recharge")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RechargeDO extends BaseDO {
    @TableField("recharge_no")
    private String rechargeNo;
    @TableField("account_id")
    private Long accountId;

    @TableField("account_name")
    private String accountName;

    @TableField("from_address")
    private String fromAddress;

    @TableField("hash")
    private String hash;

    @TableField("wallet_id")
    private Long walletId;

    @TableField("wallet_code")
    private String walletCode;

    @TableField("wallet_name")
    private String walletName;
    @TableField("wallet_address")
    private String walletAddress;
    @TableField("amount")
    private BigDecimal amount;
    @TableField("gas_amount")
    private BigDecimal gasAmount;
    @TableField("receive_amount")
    private BigDecimal receiveAmount;
    @TableField("status_")
    private Integer status;
    @TableField("remark")
    private String remark;
    @TableField("wallet_remark")
    private String walletRemark;

}

