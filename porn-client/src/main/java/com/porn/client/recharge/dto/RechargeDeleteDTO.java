package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BaseDTO;

public class RechargeDeleteDTO
        extends BaseDTO {

    protected RechargeDeleteDTO(RechargeDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public RechargeDeleteDTO() {
    }

    public static RechargeDeleteDTOBuilder<?, ?> builder() {
        return new RechargeDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeDeleteDTO;
    }

    private static final class RechargeDeleteDTOBuilderImpl extends RechargeDeleteDTOBuilder<RechargeDeleteDTO, RechargeDeleteDTOBuilderImpl> {
        private RechargeDeleteDTOBuilderImpl() {
        }

        protected RechargeDeleteDTOBuilderImpl self() {
            return this;
        }

        public RechargeDeleteDTO build() {
            return new RechargeDeleteDTO(this);
        }
    }

    public static abstract class RechargeDeleteDTOBuilder<C extends RechargeDeleteDTO, B extends RechargeDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

