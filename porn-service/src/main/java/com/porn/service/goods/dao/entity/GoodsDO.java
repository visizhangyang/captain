package com.porn.service.goods.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@TableName("porn_goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GoodsDO extends BaseDO {
    @TableField("merchant_id")
    private Long merchantId;
    @TableField("merchant_name")
    private String merchantName;
    @TableField("merchant_avatar")
    private String merchantAvatar;
    @TableField("_amount")
    private BigDecimal amount;
    @TableField("_rate")
    private BigDecimal rate;

    @TableField("free_amount")
    private BigDecimal freeAmount;

    @TableField("goods_status")
    private Integer goodsStatus;

    @TableField("account_id")
    private Long accountId;

    @TableField("account_name")
    private String accountName;
}
