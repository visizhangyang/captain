package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RechargeSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("来源地址")
    private String fromAddress;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("交易hash")
    private String hash;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("充值金额")
    private BigDecimal amount;
    @ApiModelProperty("手续费")
    private BigDecimal gasAmount;
    @ApiModelProperty("到账金额")
    private BigDecimal receiveAmount;
    @ApiModelProperty("状态, RechargeStatusEnum")
    private Integer status;
    @ApiModelProperty("更新创建时间")
    private Boolean updateCreateTime;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("钱包备注")
    private String walletRemark;

    protected RechargeSaveOrUpdateDTO(RechargeSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.fromAddress = b.fromAddress;
        this.walletCode = b.walletCode;
        this.hash = b.hash;
        this.accountId = b.accountId;
        this.amount = b.amount;
        this.gasAmount = b.gasAmount;
        this.receiveAmount = b.receiveAmount;
        this.status = b.status;
        this.updateCreateTime = b.updateCreateTime;
        this.remark = b.remark;
        this.walletRemark = b.walletRemark;
    }

    public RechargeSaveOrUpdateDTO(String fromAddress, String walletCode, String hash, Long accountId, BigDecimal amount, BigDecimal gasAmount, BigDecimal receiveAmount, Integer status, Boolean updateCreateTime, String remark, String walletRemark) {

        this.fromAddress = fromAddress;
        this.walletCode = walletCode;
        this.hash = hash;
        this.accountId = accountId;
        this.amount = amount;
        this.gasAmount = gasAmount;
        this.receiveAmount = receiveAmount;
        this.status = status;
        this.updateCreateTime = updateCreateTime;
        this.remark = remark;
        this.walletRemark = walletRemark;

    }

    public RechargeSaveOrUpdateDTO() {
    }

    public static RechargeSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RechargeSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeSaveOrUpdateDTO;
    }

    public String getFromAddress() {

        return this.fromAddress;

    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getHash() {

        return this.hash;

    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getGasAmount() {

        return this.gasAmount;

    }

    public void setGasAmount(BigDecimal gasAmount) {
        this.gasAmount = gasAmount;
    }

    public BigDecimal getReceiveAmount() {

        return this.receiveAmount;

    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getUpdateCreateTime() {

        return this.updateCreateTime;

    }

    public void setUpdateCreateTime(Boolean updateCreateTime) {
        this.updateCreateTime = updateCreateTime;
    }

    public String getRemark() {

        return this.remark;

    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWalletRemark() {

        return this.walletRemark;

    }

    public void setWalletRemark(String walletRemark) {
        this.walletRemark = walletRemark;
    }

    private static final class RechargeSaveOrUpdateDTOBuilderImpl extends RechargeSaveOrUpdateDTOBuilder<RechargeSaveOrUpdateDTO, RechargeSaveOrUpdateDTOBuilderImpl> {
        private RechargeSaveOrUpdateDTOBuilderImpl() {
        }

        protected RechargeSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RechargeSaveOrUpdateDTO build() {
            return new RechargeSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RechargeSaveOrUpdateDTOBuilder<C extends RechargeSaveOrUpdateDTO, B extends RechargeSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String fromAddress;
        private String walletCode;
        private String hash;
        private Long accountId;
        private BigDecimal amount;
        private BigDecimal gasAmount;
        private BigDecimal receiveAmount;
        private Integer status;
        private Boolean updateCreateTime;
        private String remark;
        private String walletRemark;

        public B fromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
            return self();
        }

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B hash(String hash) {
            this.hash = hash;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public B gasAmount(BigDecimal gasAmount) {
            this.gasAmount = gasAmount;
            return self();
        }

        public B receiveAmount(BigDecimal receiveAmount) {
            this.receiveAmount = receiveAmount;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B updateCreateTime(Boolean updateCreateTime) {
            this.updateCreateTime = updateCreateTime;
            return self();
        }

        public B remark(String remark) {
            this.remark = remark;
            return self();
        }

        public B walletRemark(String walletRemark) {
            this.walletRemark = walletRemark;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

