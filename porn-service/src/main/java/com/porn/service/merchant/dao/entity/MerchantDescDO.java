package com.porn.service.merchant.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@TableName("porn_merchant_desc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MerchantDescDO extends BaseDO {

    @TableField("merchant_id")
    private Long merchantId;

    @TableField("lang_type")
    private Integer langType;

    @TableField("_content")
    private String content;


}
