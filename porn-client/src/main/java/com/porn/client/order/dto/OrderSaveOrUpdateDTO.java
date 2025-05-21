
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.math.BigDecimal;

 public class OrderSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("账户id")
     private Long accountId;
    @ApiModelProperty("账户名称")
     private String accountName;
    @ApiModelProperty("账户头像")
     private String accountAvatar;
    @ApiModelProperty("商户id")
     private Long merchantId;

    @ApiModelProperty("商户名称")
     private String merchantName;

    @ApiModelProperty("商户头像")
     private String merchantAvatar;

    @ApiModelProperty("订单金额")
     private BigDecimal orderAmount;

    @ApiModelProperty("订单费率(%)")
     private BigDecimal orderRate;


    /* 16 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @ApiModelProperty("订单佣金")
    private BigDecimal freeAmount;
    @ApiModelProperty("订单状态, OrderStatusEnum")
    private Integer orderStatus;
    @ApiModelProperty("订单app播放状态, 0-未播放, 1-已播放")
    private Integer playStatus;
    @ApiModelProperty("OrderTypeEnum, 0-真人, 1-机器人")
    private Integer orderType;
    @ApiModelProperty("remark")
    private String remark;
    @ApiModelProperty("是否通知钉钉")
    private Boolean notifyMsg;
    @ApiModelProperty("收款地址钱包编码")
    private String walletCode;
    @ApiModelProperty("收款地址钱包名称")
    private String walletName;
    @ApiModelProperty("钱包地址")
    private String address;

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountAvatar(String accountAvatar) {
        this.accountAvatar = accountAvatar;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantAvatar(String merchantAvatar) {
        this.merchantAvatar = merchantAvatar;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderRate(BigDecimal orderRate) {
        this.orderRate = orderRate;
    }

    public void setFreeAmount(BigDecimal freeAmount) {
        this.freeAmount = freeAmount;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPlayStatus(Integer playStatus) {
        this.playStatus = playStatus;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setNotifyMsg(Boolean notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    protected boolean canEqual(Object other) {
        return other instanceof OrderSaveOrUpdateDTO;
    }



    /* 17 */
    protected OrderSaveOrUpdateDTO(OrderSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.accountAvatar = b.accountAvatar;
        this.merchantId = b.merchantId;
        this.merchantName = b.merchantName;
        this.merchantAvatar = b.merchantAvatar;
        this.orderAmount = b.orderAmount;
        this.orderRate = b.orderRate;
        this.freeAmount = b.freeAmount;
        this.orderStatus = b.orderStatus;
        this.playStatus = b.playStatus;
        this.orderType = b.orderType;
        this.remark = b.remark;
        this.notifyMsg = b.notifyMsg;
        this.walletCode = b.walletCode;
        this.walletName = b.walletName;
        this.address = b.address;
    }

    public static OrderSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new OrderSaveOrUpdateDTOBuilderImpl();
    }

    private static final class OrderSaveOrUpdateDTOBuilderImpl extends OrderSaveOrUpdateDTOBuilder<OrderSaveOrUpdateDTO, OrderSaveOrUpdateDTOBuilderImpl> {
        private OrderSaveOrUpdateDTOBuilderImpl() {
        }

        protected OrderSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public OrderSaveOrUpdateDTO build() {
            return new OrderSaveOrUpdateDTO(this);
        }
    }

    public static abstract class OrderSaveOrUpdateDTOBuilder<C extends OrderSaveOrUpdateDTO, B extends OrderSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String accountName;
        private String accountAvatar;
        private Long merchantId;
        private String merchantName;
        private String merchantAvatar;
        private BigDecimal orderAmount;
        private BigDecimal orderRate;
        private BigDecimal freeAmount;
        private Integer orderStatus;
        private Integer playStatus;
        private Integer orderType;
        private String remark;
        private Boolean notifyMsg;
        private String walletCode;
        private String walletName;
        private String address;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        public B accountAvatar(String accountAvatar) {
            this.accountAvatar = accountAvatar;
            return self();
        }

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B merchantName(String merchantName) {
            this.merchantName = merchantName;
            return self();
        }

        public B merchantAvatar(String merchantAvatar) {
            this.merchantAvatar = merchantAvatar;
            return self();
        }

        public B orderAmount(BigDecimal orderAmount) {
            this.orderAmount = orderAmount;
            return self();
        }

        public B orderRate(BigDecimal orderRate) {
            this.orderRate = orderRate;
            return self();
        }

        public B freeAmount(BigDecimal freeAmount) {
            this.freeAmount = freeAmount;
            return self();
        }

        public B orderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
            return self();
        }

        public B playStatus(Integer playStatus) {
            this.playStatus = playStatus;
            return self();
        }

        public B orderType(Integer orderType) {
            this.orderType = orderType;
            return self();
        }

        public B remark(String remark) {
            this.remark = remark;
            return self();
        }

        public B notifyMsg(Boolean notifyMsg) {
            this.notifyMsg = notifyMsg;
            return self();
        }

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B walletName(String walletName) {
            this.walletName = walletName;
            return self();
        }

        public B address(String address) {
            this.address = address;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public OrderSaveOrUpdateDTO(Long accountId, String accountName, String accountAvatar, Long merchantId, String merchantName, String merchantAvatar, BigDecimal orderAmount, BigDecimal orderRate, BigDecimal freeAmount, Integer orderStatus, Integer playStatus, Integer orderType, String remark, Boolean notifyMsg, String walletCode, String walletName, String address) {
        /* 18 */
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountAvatar = accountAvatar;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantAvatar = merchantAvatar;
        this.orderAmount = orderAmount;
        this.orderRate = orderRate;
        this.freeAmount = freeAmount;
        this.orderStatus = orderStatus;
        this.playStatus = playStatus;
        this.orderType = orderType;
        this.remark = remark;
        this.notifyMsg = notifyMsg;
        this.walletCode = walletCode;
        this.walletName = walletName;
        this.address = address;

    }


    public OrderSaveOrUpdateDTO() {
    }



    public Long getAccountId() {
        /* 23 */
        return this.accountId;

    }


    public String getAccountName() {
        /* 26 */
        return this.accountName;

    }


    public String getAccountAvatar() {
        /* 29 */
        return this.accountAvatar;

    }


    public Long getMerchantId() {
        /* 32 */
        return this.merchantId;

    }


    public String getMerchantName() {
        /* 35 */
        return this.merchantName;

    }


    public String getMerchantAvatar() {
        /* 38 */
        return this.merchantAvatar;

    }


    public BigDecimal getOrderAmount() {
        /* 41 */
        return this.orderAmount;

    }


    public BigDecimal getOrderRate() {
        /* 44 */
        return this.orderRate;

    }


    public BigDecimal getFreeAmount() {
        /* 47 */
        return this.freeAmount;

    }


    public Integer getOrderStatus() {
        /* 50 */
        return this.orderStatus;

    }


    public Integer getPlayStatus() {
        /* 53 */
        return this.playStatus;

    }


    public Integer getOrderType() {
        /* 56 */
        return this.orderType;

    }


    public String getRemark() {
        /* 59 */
        return this.remark;

    }


    public Boolean getNotifyMsg() {
        /* 62 */
        return this.notifyMsg;

    }


    public String getWalletCode() {
        /* 65 */
        return this.walletCode;

    }


    public String getWalletName() {
        /* 68 */
        return this.walletName;

    }


    public String getAddress() {
        /* 71 */
        return this.address;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/OrderSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */