package com.porn.client.order.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.util.List;

public class OrderQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("状态")
    private Integer orderStatus;

    @ApiModelProperty("OrderTypeEnum, 0-真人, 1-机器人")
    private Integer orderType;

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("备注")
    private String lkRemark;
    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;
    @ApiModelProperty("商户ID列表")
    private List<Long> merchantIdList;
    @ApiModelProperty("起始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty("订单状态, OrderStatusEnum")
    private List<Integer> orderStatusList;
    @ApiModelProperty("收款地址钱包编码")
    private String walletCode;

    protected OrderQueryPageDTO(OrderQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.orderStatus = b.orderStatus;
        this.orderType = b.orderType;
        this.accountId = b.accountId;
        this.lkAccountName = b.lkAccountName;
        this.lkRemark = b.lkRemark;
        this.accountIdList = b.accountIdList;
        this.merchantIdList = b.merchantIdList;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
        this.orderStatusList = b.orderStatusList;
        this.walletCode = b.walletCode;
    }

    public OrderQueryPageDTO(Long merchantId, Integer orderStatus, Integer orderType, Long accountId, String lkAccountName, String lkRemark, List<Long> accountIdList, List<Long> merchantIdList, LocalDateTime startTime, LocalDateTime endTime, List<Integer> orderStatusList, String walletCode) {

        this.merchantId = merchantId;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.accountId = accountId;
        this.lkAccountName = lkAccountName;
        this.lkRemark = lkRemark;
        this.accountIdList = accountIdList;
        this.merchantIdList = merchantIdList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatusList = orderStatusList;
        this.walletCode = walletCode;

    }

    public OrderQueryPageDTO() {
    }

    public static OrderQueryPageDTOBuilder<?, ?> builder() {
        return new OrderQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OrderQueryPageDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getOrderStatus() {

        return this.orderStatus;

    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderType() {

        return this.orderType;

    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLkAccountName() {

        return this.lkAccountName;

    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public String getLkRemark() {

        return this.lkRemark;

    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
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

    public List<Integer> getOrderStatusList() {

        return this.orderStatusList;

    }

    public void setOrderStatusList(List<Integer> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    private static final class OrderQueryPageDTOBuilderImpl extends OrderQueryPageDTOBuilder<OrderQueryPageDTO, OrderQueryPageDTOBuilderImpl> {
        private OrderQueryPageDTOBuilderImpl() {
        }

        protected OrderQueryPageDTOBuilderImpl self() {
            return this;
        }

        public OrderQueryPageDTO build() {
            return new OrderQueryPageDTO(this);
        }
    }

    public static abstract class OrderQueryPageDTOBuilder<C extends OrderQueryPageDTO, B extends OrderQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long merchantId;
        private Integer orderStatus;
        private Integer orderType;
        private Long accountId;
        private String lkAccountName;
        private String lkRemark;
        private List<Long> accountIdList;
        private List<Long> merchantIdList;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private List<Integer> orderStatusList;
        private String walletCode;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B orderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
            return self();
        }

        public B orderType(Integer orderType) {
            this.orderType = orderType;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B lkAccountName(String lkAccountName) {
            this.lkAccountName = lkAccountName;
            return self();
        }

        public B lkRemark(String lkRemark) {
            this.lkRemark = lkRemark;
            return self();
        }

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
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

        public B orderStatusList(List<Integer> orderStatusList) {
            this.orderStatusList = orderStatusList;
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

