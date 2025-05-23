package com.porn.service.rhamount.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


@TableName("porn_rhamount")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RhAmountDO
        extends BaseDO {

    @TableField("_amount")
    private BigDecimal amount;

    @TableField("sort_no")
    private Integer sortNo;


}

