
package com.porn.client.merchant.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class MerchantVo extends BaseVo {
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
    @ApiModelProperty("商户类型, MerchantTypeEnum")
    private Integer merchantType;
    @ApiModelProperty("商户标签")
    private String merchantTag;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/vo/MerchantVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */