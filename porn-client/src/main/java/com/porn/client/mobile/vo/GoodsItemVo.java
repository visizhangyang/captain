package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GoodsItemVo implements Serializable {
    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("商户名称")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("头像地址")
    private String avatarUrl;

    @ApiModelProperty("保证金")
    private BigDecimal ensureAmount;

    @ApiModelProperty("费率范围")
    private String rateRange;

    @ApiModelProperty("地区名称")
    private String areaName;

    @ApiModelProperty("会员级别")
    private Integer memberLevel;

    @ApiModelProperty("会员级别名称")
    private String memberLevelName;

    @ApiModelProperty("认证级别")
    private Integer authLevel;

    @ApiModelProperty("认证级别名称")
    private String authLevelName;

    @ApiModelProperty("邮箱认证")
    private Integer mailAuth;

    @ApiModelProperty("手机认证")
    private Integer phoneAuth;

    @ApiModelProperty("kyc认证")
    private Integer kycAuth;

    @ApiModelProperty("地址认证")
    private Integer addressAuth;

    @ApiModelProperty("累计订单数")
    private Long accumulateCount;

    @ApiModelProperty("累计金额")
    private BigDecimal accumulateAmount;

    @ApiModelProperty("会员状态")
    private Integer status;

    @ApiModelProperty("今日交易总金额")
    private BigDecimal todayTotalAmount;

    @ApiModelProperty("今日成交订单数")
    private Long todayOrderCount;

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("金额")
    private BigDecimal goodsAmount;

    @ApiModelProperty("费率(%)")
    private BigDecimal goodsRate;

    @ApiModelProperty("佣金")
    private BigDecimal goodsFreeAmount;

    @ApiModelProperty("商户标签")
    private String merchantTag;

}