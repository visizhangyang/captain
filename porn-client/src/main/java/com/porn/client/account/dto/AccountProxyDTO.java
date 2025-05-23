package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;

public class AccountProxyDTO
        extends BaseDTO {

    protected AccountProxyDTO(AccountProxyDTOBuilder<?, ?> b) {
        super(b);
    }

    public AccountProxyDTO() {
    }

    public static AccountProxyDTOBuilder<?, ?> builder() {
        return new AccountProxyDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountProxyDTO;
    }

    private static final class AccountProxyDTOBuilderImpl extends AccountProxyDTOBuilder<AccountProxyDTO, AccountProxyDTOBuilderImpl> {
        private AccountProxyDTOBuilderImpl() {
        }

        protected AccountProxyDTOBuilderImpl self() {
            return this;
        }

        public AccountProxyDTO build() {
            return new AccountProxyDTO(this);
        }
    }

    public static abstract class AccountProxyDTOBuilder<C extends AccountProxyDTO, B extends AccountProxyDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

