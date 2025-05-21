
package com.porn.client.order.vo;
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
 public class OrderVo extends BaseVo {
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
    @ApiModelProperty("账户级别")
     private Integer accountLevel;
    @ApiModelProperty("商户id")
     private Long merchantId;
    @ApiModelProperty("商户名称")
     private String merchantName;

    @ApiModelProperty("商户头像")
     private String merchantAvatar;

    @ApiModelProperty("商户头像")
     private String merchantAvatarUrl;

    @ApiModelProperty("订单金额")
    private BigDecimal orderAmount;
    @ApiModelProperty("订单费率(%)")
    private BigDecimal orderRate;
    @ApiModelProperty("订单佣金")
    private BigDecimal freeAmount;
    @ApiModelProperty("订单状态, OrderStatusEnum")
    private Integer orderStatus;
    @ApiModelProperty("收款地址")
    private String receiveAddress;
    @ApiModelProperty("播放状态, 0-未播放, 1-已播放")
    private Integer playStatus;
    @ApiModelProperty("OrderTypeEnum, 0-真人, 1-机器人")
    private Integer orderType;
    @ApiModelProperty("remark")
    private String remark;
    @ApiModelProperty("收款地址(账户的)")
    private String address;
    @ApiModelProperty("收款地址钱包编码")
    private String walletCode;
    @ApiModelProperty("收款地址钱包名称")
    private String walletName;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/vo/OrderVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */