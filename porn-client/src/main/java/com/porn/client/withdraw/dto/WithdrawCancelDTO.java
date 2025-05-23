package com.porn.client.withdraw.dto;

import com.porn.client.common.dto.BaseDTO;

public class WithdrawCancelDTO
        extends BaseDTO {

    protected WithdrawCancelDTO(WithdrawCancelDTOBuilder<?, ?> b) {
        super(b);
    }

    public WithdrawCancelDTO() {
    }

    public static WithdrawCancelDTOBuilder<?, ?> builder() {
        return new WithdrawCancelDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawCancelDTO;
    }

    private static final class WithdrawCancelDTOBuilderImpl extends WithdrawCancelDTOBuilder<WithdrawCancelDTO, WithdrawCancelDTOBuilderImpl> {
        private WithdrawCancelDTOBuilderImpl() {
        }

        protected WithdrawCancelDTOBuilderImpl self() {
            return this;
        }

        public WithdrawCancelDTO build() {
            return new WithdrawCancelDTO(this);
        }
    }

    public static abstract class WithdrawCancelDTOBuilder<C extends WithdrawCancelDTO, B extends WithdrawCancelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

