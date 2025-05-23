package com.porn.service.account.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_account_wallet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AccountWalletDO extends BaseDO {

    @TableField("account_id")
    private Long accountId;

    @TableField("wallet_code")
    private String walletCode;

    @TableField("wallet_name")
    private String walletName;

    @TableField("address")
    private String address;

}

