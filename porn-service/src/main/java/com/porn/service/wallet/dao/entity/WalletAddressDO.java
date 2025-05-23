package com.porn.service.wallet.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@TableName("porn_wallet_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WalletAddressDO extends BaseDO {
    @TableField("name")
    private String name;
    @TableField("code")
    private String code;

    @TableField("address")
    private String address;

    @TableField("address_status")
    private Integer addressStatus;

    @TableField("lock_time")
    private LocalDateTime lockTime;

    @TableField("status_")
    private Integer status;

    @TableField("version")
    private Long version;

    @TableField("remark")
    private String remark;

}
