package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MerchantTradeItemVo implements Serializable {
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("账户id")
    private Long accountId;
    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("账户头像")
    private String accountAvatar;
    @ApiModelProperty("账户头像")
    private String accountAvatarUrl;
    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty("订单费率(%)")
    private BigDecimal orderRate;
    @ApiModelProperty("订单佣金")
    private BigDecimal freeAmount;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/MerchantTradeItemVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */