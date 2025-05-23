package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class AccountAddOrSubDTO extends BaseDTO {

    @ApiModelProperty("0-加, 1-减")
    private Integer type;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    protected AccountAddOrSubDTO(AccountAddOrSubDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.amount = b.amount;
    }

    public AccountAddOrSubDTO() {
    }

    public AccountAddOrSubDTO(Integer type, BigDecimal amount) {

        this.type = type;
        this.amount = amount;

    }

    public static AccountAddOrSubDTOBuilder<?, ?> builder() {
        return new AccountAddOrSubDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountAddOrSubDTO;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {

        this.type = type;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private static final class AccountAddOrSubDTOBuilderImpl extends AccountAddOrSubDTOBuilder<AccountAddOrSubDTO, AccountAddOrSubDTOBuilderImpl> {
        private AccountAddOrSubDTOBuilderImpl() {
        }

        protected AccountAddOrSubDTOBuilderImpl self() {
            return this;
        }

        public AccountAddOrSubDTO build() {
            return new AccountAddOrSubDTO(this);
        }
    }

    public static abstract class AccountAddOrSubDTOBuilder<C extends AccountAddOrSubDTO, B extends AccountAddOrSubDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;
        private BigDecimal amount;

        public B type(Integer type) {
            this.type = type;
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

