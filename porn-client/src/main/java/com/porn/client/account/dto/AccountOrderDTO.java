package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountOrderDTO
        extends BaseDTO {

    @ApiModelProperty("订单ID")
    private Long orderId;

    protected AccountOrderDTO(AccountOrderDTOBuilder<?, ?> b) {
        super(b);
        this.orderId = b.orderId;
    }

    public AccountOrderDTO(Long orderId) {

        this.orderId = orderId;

    }

    public AccountOrderDTO() {
    }

    public static AccountOrderDTOBuilder<?, ?> builder() {
        return new AccountOrderDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountOrderDTO;
    }

    public Long getOrderId() {

        return this.orderId;

    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    private static final class AccountOrderDTOBuilderImpl extends AccountOrderDTOBuilder<AccountOrderDTO, AccountOrderDTOBuilderImpl> {
        private AccountOrderDTOBuilderImpl() {
        }

        protected AccountOrderDTOBuilderImpl self() {
            return this;
        }

        public AccountOrderDTO build() {
            return new AccountOrderDTO(this);
        }
    }

    public static abstract class AccountOrderDTOBuilder<C extends AccountOrderDTO, B extends AccountOrderDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long orderId;

        public B orderId(Long orderId) {
            this.orderId = orderId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

