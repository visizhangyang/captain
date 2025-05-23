package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AccountFreezeDTO extends BaseDTO {

    @ApiModelProperty("0-冻结, 1-解冻")
    private Integer freezeType;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    protected AccountFreezeDTO(AccountFreezeDTOBuilder<?, ?> b) {
        super(b);
        this.freezeType = b.freezeType;
        this.amount = b.amount;
    }

    public AccountFreezeDTO() {
    }

    public AccountFreezeDTO(Integer freezeType, BigDecimal amount) {

        this.freezeType = freezeType;
        this.amount = amount;

    }

    public static AccountFreezeDTOBuilder<?, ?> builder() {
        return new AccountFreezeDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountFreezeDTO;
    }

    public Integer getFreezeType() {

        return this.freezeType;

    }

    public void setFreezeType(Integer freezeType) {

        this.freezeType = freezeType;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private static final class AccountFreezeDTOBuilderImpl extends AccountFreezeDTOBuilder<AccountFreezeDTO, AccountFreezeDTOBuilderImpl> {
        private AccountFreezeDTOBuilderImpl() {
        }

        protected AccountFreezeDTOBuilderImpl self() {
            return this;
        }

        public AccountFreezeDTO build() {
            return new AccountFreezeDTO(this);
        }
    }

    public static abstract class AccountFreezeDTOBuilder<C extends AccountFreezeDTO, B extends AccountFreezeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer freezeType;
        private BigDecimal amount;

        public B freezeType(Integer freezeType) {
            this.freezeType = freezeType;
            return self();
        }

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

