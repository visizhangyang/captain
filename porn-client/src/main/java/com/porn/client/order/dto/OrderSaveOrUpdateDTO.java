package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    public OrderSaveOrUpdateDTO(Long accountId, String accountName, String accountAvatar, Long merchantId, String merchantName, String merchantAvatar, BigDecimal orderAmount, BigDecimal orderRate, BigDecimal freeAmount, Integer orderStatus, Integer playStatus, Integer orderType, String remark, Boolean notifyMsg, String walletCode, String walletName, String address) {

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

    public static OrderSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new OrderSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {

        return this.accountName;

    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountAvatar() {

        return this.accountAvatar;

    }

    public void setAccountAvatar(String accountAvatar) {
        this.accountAvatar = accountAvatar;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return this.merchantName;

    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAvatar() {

        return this.merchantAvatar;

    }

    public void setMerchantAvatar(String merchantAvatar) {
        this.merchantAvatar = merchantAvatar;
    }

    public BigDecimal getOrderAmount() {

        return this.orderAmount;

    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderRate() {

        return this.orderRate;

    }

    public void setOrderRate(BigDecimal orderRate) {
        this.orderRate = orderRate;
    }

    public BigDecimal getFreeAmount() {

        return this.freeAmount;

    }

    public void setFreeAmount(BigDecimal freeAmount) {
        this.freeAmount = freeAmount;
    }

    public Integer getOrderStatus() {

        return this.orderStatus;

    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPlayStatus() {

        return this.playStatus;

    }

    public void setPlayStatus(Integer playStatus) {
        this.playStatus = playStatus;
    }

    public Integer getOrderType() {

        return this.orderType;

    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getRemark() {

        return this.remark;

    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getNotifyMsg() {

        return this.notifyMsg;

    }

    public void setNotifyMsg(Boolean notifyMsg) {
        this.notifyMsg = notifyMsg;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getWalletName() {

        return this.walletName;

    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getAddress() {

        return this.address;

    }

    public void setAddress(String address) {
        this.address = address;
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
}

