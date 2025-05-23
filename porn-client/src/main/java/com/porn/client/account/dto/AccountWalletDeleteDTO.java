package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;

public class AccountWalletDeleteDTO
        extends BaseDTO {

    protected AccountWalletDeleteDTO(AccountWalletDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public AccountWalletDeleteDTO() {
    }

    public static AccountWalletDeleteDTOBuilder<?, ?> builder() {
        return new AccountWalletDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletDeleteDTO;
    }

    private static final class AccountWalletDeleteDTOBuilderImpl extends AccountWalletDeleteDTOBuilder<AccountWalletDeleteDTO, AccountWalletDeleteDTOBuilderImpl> {
        private AccountWalletDeleteDTOBuilderImpl() {
        }

        protected AccountWalletDeleteDTOBuilderImpl self() {
            return this;
        }

        public AccountWalletDeleteDTO build() {
            return new AccountWalletDeleteDTO(this);
        }
    }

    public static abstract class AccountWalletDeleteDTOBuilder<C extends AccountWalletDeleteDTO, B extends AccountWalletDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

