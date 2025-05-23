package com.porn.client.withdraw.dto;

import com.porn.client.common.dto.BaseDTO;

public class WithdrawDeleteDTO
        extends BaseDTO {

    protected WithdrawDeleteDTO(WithdrawDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public WithdrawDeleteDTO() {
    }

    public static WithdrawDeleteDTOBuilder<?, ?> builder() {
        return new WithdrawDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WithdrawDeleteDTO;
    }

    private static final class WithdrawDeleteDTOBuilderImpl extends WithdrawDeleteDTOBuilder<WithdrawDeleteDTO, WithdrawDeleteDTOBuilderImpl> {
        private WithdrawDeleteDTOBuilderImpl() {
        }

        protected WithdrawDeleteDTOBuilderImpl self() {
            return this;
        }

        public WithdrawDeleteDTO build() {
            return new WithdrawDeleteDTO(this);
        }
    }

    public static abstract class WithdrawDeleteDTOBuilder<C extends WithdrawDeleteDTO, B extends WithdrawDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

