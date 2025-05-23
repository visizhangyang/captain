package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;

public class AccountclearReceiveAddrDTO
        extends BaseDTO {

    protected AccountclearReceiveAddrDTO(AccountclearReceiveAddrDTOBuilder<?, ?> b) {
        super(b);
    }

    public AccountclearReceiveAddrDTO() {
    }

    public static AccountclearReceiveAddrDTOBuilder<?, ?> builder() {
        return new AccountclearReceiveAddrDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountclearReceiveAddrDTO;
    }

    private static final class AccountclearReceiveAddrDTOBuilderImpl extends AccountclearReceiveAddrDTOBuilder<AccountclearReceiveAddrDTO, AccountclearReceiveAddrDTOBuilderImpl> {
        private AccountclearReceiveAddrDTOBuilderImpl() {
        }

        protected AccountclearReceiveAddrDTOBuilderImpl self() {
            return this;
        }

        public AccountclearReceiveAddrDTO build() {
            return new AccountclearReceiveAddrDTO(this);
        }
    }

    public static abstract class AccountclearReceiveAddrDTOBuilder<C extends AccountclearReceiveAddrDTO, B extends AccountclearReceiveAddrDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

