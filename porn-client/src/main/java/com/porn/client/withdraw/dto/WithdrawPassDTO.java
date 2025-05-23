package com.porn.client.withdraw.dto;

import com.porn.client.common.dto.BaseDTO;

public class WithdrawPassDTO
        extends BaseDTO {

    protected WithdrawPassDTO(WithdrawPassDTOBuilder<?, ?> b) {
        super(b);
    }

    public WithdrawPassDTO() {
    }

    public static WithdrawPassDTOBuilder<?, ?> builder() {
        return new WithdrawPassDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawPassDTO;
    }

    private static final class WithdrawPassDTOBuilderImpl extends WithdrawPassDTOBuilder<WithdrawPassDTO, WithdrawPassDTOBuilderImpl> {
        private WithdrawPassDTOBuilderImpl() {
        }

        protected WithdrawPassDTOBuilderImpl self() {
            return this;
        }

        public WithdrawPassDTO build() {
            return new WithdrawPassDTO(this);
        }
    }

    public static abstract class WithdrawPassDTOBuilder<C extends WithdrawPassDTO, B extends WithdrawPassDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

