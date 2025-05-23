package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;

public class AccountStatisticsDTO
        extends BaseDTO {

    protected AccountStatisticsDTO(AccountStatisticsDTOBuilder<?, ?> b) {
        super(b);
    }

    public static AccountStatisticsDTOBuilder<?, ?> builder() {
        return new AccountStatisticsDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountStatisticsDTO;
    }

    private static final class AccountStatisticsDTOBuilderImpl extends AccountStatisticsDTOBuilder<AccountStatisticsDTO, AccountStatisticsDTOBuilderImpl> {
        private AccountStatisticsDTOBuilderImpl() {
        }

        protected AccountStatisticsDTOBuilderImpl self() {
            return this;
        }

        public AccountStatisticsDTO build() {
            return new AccountStatisticsDTO(this);
        }
    }

    public static abstract class AccountStatisticsDTOBuilder<C extends AccountStatisticsDTO, B extends AccountStatisticsDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

