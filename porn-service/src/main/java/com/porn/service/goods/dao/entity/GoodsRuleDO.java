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

@TableName("porn_goodsrule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class GoodsRuleDO extends BaseDO {

    @TableField("merchant_id")
    private Long merchantId;

    @TableField("min_goodscount")
    private Integer minGoodsCount;

    @TableField("max_goodscount")
    private Integer maxGoodsCount;

    @TableField("min_amount")
    private BigDecimal minAmount;

    @TableField("max_amount")
    private BigDecimal maxAmount;

    @TableField("status_")
    private Integer status;


}
