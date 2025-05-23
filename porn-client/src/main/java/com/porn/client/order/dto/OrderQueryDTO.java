package com.porn.client.order.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

public class OrderQueryDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;
    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;
    @ApiModelProperty("商户ID")
    private Long merchantId;
    @ApiModelProperty("商户ID列表")
    private List<Long> merchantIdList;
    @ApiModelProperty("起始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("订单状态, OrderStatusEnum")
    private Integer orderStatus;

    @ApiModelProperty("订单状态, OrderStatusEnum")
    private List<Integer> orderStatusList;

    @ApiModelProperty("OrderTypeEnum, 0-真人, 1-机器人")
    private Integer orderType;

    @ApiModelProperty("收款地址钱包编码")
    private String walletCode;

    protected OrderQueryDTO(OrderQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.merchantId = b.merchantId;
        this.merchantIdList = b.merchantIdList;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
        this.orderStatus = b.orderStatus;
        this.orderStatusList = b.orderStatusList;
        this.orderType = b.orderType;
        this.walletCode = b.walletCode;
    }

    public OrderQueryDTO(Long accountId, List<Long> accountIdList, Long merchantId, List<Long> merchantIdList, LocalDateTime startTime, LocalDateTime endTime, Integer orderStatus, List<Integer> orderStatusList, Integer orderType, String walletCode) {

        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.merchantId = merchantId;
        this.merchantIdList = merchantIdList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
        this.orderStatusList = orderStatusList;
        this.orderType = orderType;
        this.walletCode = walletCode;

    }

    public OrderQueryDTO() {
    }

    public static OrderQueryDTOBuilder<?, ?> builder() {
        return new OrderQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public List<Long> getMerchantIdList() {

        return this.merchantIdList;

    }

    public void setMerchantIdList(List<Long> merchantIdList) {
        this.merchantIdList = merchantIdList;
    }

    public LocalDateTime getStartTime() {

        return this.startTime;

    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {

        return this.endTime;

    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderStatus() {

        return this.orderStatus;

    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Integer> getOrderStatusList() {

        return this.orderStatusList;

    }

    public void setOrderStatusList(List<Integer> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public Integer getOrderType() {

        return this.orderType;

    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    private static final class OrderQueryDTOBuilderImpl extends OrderQueryDTOBuilder<OrderQueryDTO, OrderQueryDTOBuilderImpl> {
        private OrderQueryDTOBuilderImpl() {
        }

        protected OrderQueryDTOBuilderImpl self() {
            return this;
        }

        public OrderQueryDTO build() {
            return new OrderQueryDTO(this);
        }
    }

    public static abstract class OrderQueryDTOBuilder<C extends OrderQueryDTO, B extends OrderQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private List<Long> accountIdList;
        private Long merchantId;
        private List<Long> merchantIdList;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer orderStatus;
        private List<Integer> orderStatusList;
        private Integer orderType;
        private String walletCode;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B merchantIdList(List<Long> merchantIdList) {
            this.merchantIdList = merchantIdList;
            return self();
        }

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        public B endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return self();
        }

        public B orderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
            return self();
        }

        public B orderStatusList(List<Integer> orderStatusList) {
            this.orderStatusList = orderStatusList;
            return self();
        }

        public B orderType(Integer orderType) {
            this.orderType = orderType;
            return self();
        }

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

