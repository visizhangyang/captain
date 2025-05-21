
package com.porn.service.merchant.dao.entity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.porn.service.common.entity.BaseDO;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@TableName("porn_merchant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class MerchantDO extends BaseDO {
    @TableField("name")
     private String name;
    @TableField("avatar")
     private String avatar;
    @TableField("rate_range")
     private String rateRange;
    @TableField("ensure_amount")
     private BigDecimal ensureAmount;
    @TableField("area_name")
     private String areaName;
    @TableField("member_level")
     private Integer memberLevel;

    @TableField("member_levelname")
     private String memberLevelName;

    @TableField("auth_level")
     private Integer authLevel;

    @TableField("auth_levelname")
     private String authLevelName;

    @TableField("mail_auth")
    private Integer mailAuth;
    @TableField("phone_auth")
    private Integer phoneAuth;
    @TableField("kyc_auth")
    private Integer kycAuth;
    @TableField("address_auth")
    private Integer addressAuth;
    @TableField("accumulate_count")
    private Long accumulateCount;
    @TableField("accumulate_amount")
    private BigDecimal accumulateAmount;
    @TableField("status_")
    private Integer status;
    @TableField("merchant_type")
    private Integer merchantType;
    @TableField("merchant_tag")
    private String merchantTag;

}
