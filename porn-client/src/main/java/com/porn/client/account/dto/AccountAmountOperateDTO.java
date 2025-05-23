package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import com.porn.client.stream.enums.StreamTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AccountAmountOperateDTO extends BaseDTO {

    @ApiModelProperty("金额类型")
    private Integer amountType;

    @ApiModelProperty("操作金额")
    private BigDecimal operateAmount;

    @ApiModelProperty("业务Id")
    private Long bizId;

    @ApiModelProperty("提现到账")
    private StreamTypeEnum streamTypeEnum;

    protected AccountAmountOperateDTO(AccountAmountOperateDTOBuilder<?, ?> b) {
        super(b);
        this.amountType = b.amountType;
        this.operateAmount = b.operateAmount;
        this.bizId = b.bizId;
        this.streamTypeEnum = b.streamTypeEnum;
    }

    public AccountAmountOperateDTO(Integer amountType, BigDecimal operateAmount, Long bizId, StreamTypeEnum streamTypeEnum) {

        this.amountType = amountType;
        this.operateAmount = operateAmount;
        this.bizId = bizId;
        this.streamTypeEnum = streamTypeEnum;

    }

    public AccountAmountOperateDTO() {
    }

    public static AccountAmountOperateDTOBuilder<?, ?> builder() {
        return new AccountAmountOperateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountAmountOperateDTO;
    }

    public Integer getAmountType() {

        return this.amountType;

    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getOperateAmount() {

        return this.operateAmount;

    }

    public void setOperateAmount(BigDecimal operateAmount) {
        this.operateAmount = operateAmount;
    }

    public Long getBizId() {

        return this.bizId;

    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public StreamTypeEnum getStreamTypeEnum() {

        return this.streamTypeEnum;

    }

    public void setStreamTypeEnum(StreamTypeEnum streamTypeEnum) {
        this.streamTypeEnum = streamTypeEnum;
    }

    private static final class AccountAmountOperateDTOBuilderImpl extends AccountAmountOperateDTOBuilder<AccountAmountOperateDTO, AccountAmountOperateDTOBuilderImpl> {
        private AccountAmountOperateDTOBuilderImpl() {
        }

        protected AccountAmountOperateDTOBuilderImpl self() {
            return this;
        }

        public AccountAmountOperateDTO build() {
            return new AccountAmountOperateDTO(this);
        }
    }

    public static abstract class AccountAmountOperateDTOBuilder<C extends AccountAmountOperateDTO, B extends AccountAmountOperateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer amountType;
        private BigDecimal operateAmount;
        private Long bizId;
        private StreamTypeEnum streamTypeEnum;

        public B amountType(Integer amountType) {
            this.amountType = amountType;
            return self();
        }

        public B operateAmount(BigDecimal operateAmount) {
            this.operateAmount = operateAmount;
            return self();
        }

        public B bizId(Long bizId) {
            this.bizId = bizId;
            return self();
        }

        public B streamTypeEnum(StreamTypeEnum streamTypeEnum) {
            this.streamTypeEnum = streamTypeEnum;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

