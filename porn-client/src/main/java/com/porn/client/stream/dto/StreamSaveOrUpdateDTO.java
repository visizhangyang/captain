package com.porn.client.stream.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class StreamSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("操作前总余额")
    private BigDecimal beforeTotalBalance;

    @ApiModelProperty("操作前可用余额")
    private BigDecimal beforeAvailableBalance;

    @ApiModelProperty("操作前冻结余额")
    private BigDecimal beforeFreezeBalance;

    @ApiModelProperty("操作后总余额")
    private BigDecimal afterTotalBalance;
    @ApiModelProperty("操作后可用余额")
    private BigDecimal afterAvailableBalance;
    @ApiModelProperty("操作后冻结余额")
    private BigDecimal afterFreezeBalance;
    @ApiModelProperty("业务ID")
    private Long bizId;
    @ApiModelProperty("金额")
    private BigDecimal amount;
    @ApiModelProperty("类型, StreamTypeEnum")
    private Integer type;
    @ApiModelProperty("标识, StreamTypeEnum")
    private Integer flag;

    protected StreamSaveOrUpdateDTO(StreamSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.beforeTotalBalance = b.beforeTotalBalance;
        this.beforeAvailableBalance = b.beforeAvailableBalance;
        this.beforeFreezeBalance = b.beforeFreezeBalance;
        this.afterTotalBalance = b.afterTotalBalance;
        this.afterAvailableBalance = b.afterAvailableBalance;
        this.afterFreezeBalance = b.afterFreezeBalance;
        this.bizId = b.bizId;
        this.amount = b.amount;
        this.type = b.type;
        this.flag = b.flag;
    }

    public StreamSaveOrUpdateDTO(Long accountId, String accountName, BigDecimal beforeTotalBalance, BigDecimal beforeAvailableBalance, BigDecimal beforeFreezeBalance, BigDecimal afterTotalBalance, BigDecimal afterAvailableBalance, BigDecimal afterFreezeBalance, Long bizId, BigDecimal amount, Integer type, Integer flag) {

        this.accountId = accountId;
        this.accountName = accountName;
        this.beforeTotalBalance = beforeTotalBalance;
        this.beforeAvailableBalance = beforeAvailableBalance;
        this.beforeFreezeBalance = beforeFreezeBalance;
        this.afterTotalBalance = afterTotalBalance;
        this.afterAvailableBalance = afterAvailableBalance;
        this.afterFreezeBalance = afterFreezeBalance;
        this.bizId = bizId;
        this.amount = amount;
        this.type = type;
        this.flag = flag;

    }

    public StreamSaveOrUpdateDTO() {
    }

    public static StreamSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new StreamSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof StreamSaveOrUpdateDTO;
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

    public BigDecimal getBeforeTotalBalance() {

        return this.beforeTotalBalance;

    }

    public void setBeforeTotalBalance(BigDecimal beforeTotalBalance) {
        this.beforeTotalBalance = beforeTotalBalance;
    }

    public BigDecimal getBeforeAvailableBalance() {

        return this.beforeAvailableBalance;

    }

    public void setBeforeAvailableBalance(BigDecimal beforeAvailableBalance) {
        this.beforeAvailableBalance = beforeAvailableBalance;
    }

    public BigDecimal getBeforeFreezeBalance() {

        return this.beforeFreezeBalance;

    }

    public void setBeforeFreezeBalance(BigDecimal beforeFreezeBalance) {
        this.beforeFreezeBalance = beforeFreezeBalance;
    }

    public BigDecimal getAfterTotalBalance() {

        return this.afterTotalBalance;

    }

    public void setAfterTotalBalance(BigDecimal afterTotalBalance) {
        this.afterTotalBalance = afterTotalBalance;
    }

    public BigDecimal getAfterAvailableBalance() {

        return this.afterAvailableBalance;

    }

    public void setAfterAvailableBalance(BigDecimal afterAvailableBalance) {
        this.afterAvailableBalance = afterAvailableBalance;
    }

    public BigDecimal getAfterFreezeBalance() {

        return this.afterFreezeBalance;

    }

    public void setAfterFreezeBalance(BigDecimal afterFreezeBalance) {
        this.afterFreezeBalance = afterFreezeBalance;
    }

    public Long getBizId() {

        return this.bizId;

    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {

        return this.flag;

    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    private static final class StreamSaveOrUpdateDTOBuilderImpl extends StreamSaveOrUpdateDTOBuilder<StreamSaveOrUpdateDTO, StreamSaveOrUpdateDTOBuilderImpl> {
        private StreamSaveOrUpdateDTOBuilderImpl() {
        }

        protected StreamSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public StreamSaveOrUpdateDTO build() {
            return new StreamSaveOrUpdateDTO(this);
        }
    }

    public static abstract class StreamSaveOrUpdateDTOBuilder<C extends StreamSaveOrUpdateDTO, B extends StreamSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private String accountName;
        private BigDecimal beforeTotalBalance;
        private BigDecimal beforeAvailableBalance;
        private BigDecimal beforeFreezeBalance;
        private BigDecimal afterTotalBalance;
        private BigDecimal afterAvailableBalance;
        private BigDecimal afterFreezeBalance;
        private Long bizId;
        private BigDecimal amount;
        private Integer type;
        private Integer flag;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        public B beforeTotalBalance(BigDecimal beforeTotalBalance) {
            this.beforeTotalBalance = beforeTotalBalance;
            return self();
        }

        public B beforeAvailableBalance(BigDecimal beforeAvailableBalance) {
            this.beforeAvailableBalance = beforeAvailableBalance;
            return self();
        }

        public B beforeFreezeBalance(BigDecimal beforeFreezeBalance) {
            this.beforeFreezeBalance = beforeFreezeBalance;
            return self();
        }

        public B afterTotalBalance(BigDecimal afterTotalBalance) {
            this.afterTotalBalance = afterTotalBalance;
            return self();
        }

        public B afterAvailableBalance(BigDecimal afterAvailableBalance) {
            this.afterAvailableBalance = afterAvailableBalance;
            return self();
        }

        public B afterFreezeBalance(BigDecimal afterFreezeBalance) {
            this.afterFreezeBalance = afterFreezeBalance;
            return self();
        }

        public B bizId(Long bizId) {
            this.bizId = bizId;
            return self();
        }

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B flag(Integer flag) {
            this.flag = flag;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

