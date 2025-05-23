package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;

public class AccountForceStopPlanDTO
        extends BaseDTO {

    protected AccountForceStopPlanDTO(AccountForceStopPlanDTOBuilder<?, ?> b) {
        super(b);
    }

    public AccountForceStopPlanDTO() {
    }

    public static AccountForceStopPlanDTOBuilder<?, ?> builder() {
        return new AccountForceStopPlanDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountForceStopPlanDTO;
    }

    private static final class AccountForceStopPlanDTOBuilderImpl extends AccountForceStopPlanDTOBuilder<AccountForceStopPlanDTO, AccountForceStopPlanDTOBuilderImpl> {
        private AccountForceStopPlanDTOBuilderImpl() {
        }

        protected AccountForceStopPlanDTOBuilderImpl self() {
            return this;
        }

        public AccountForceStopPlanDTO build() {
            return new AccountForceStopPlanDTO(this);
        }
    }

    public static abstract class AccountForceStopPlanDTOBuilder<C extends AccountForceStopPlanDTO, B extends AccountForceStopPlanDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

