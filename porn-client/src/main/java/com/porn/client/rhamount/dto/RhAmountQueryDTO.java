package com.porn.client.rhamount.dto;

import com.porn.client.common.dto.BaseDTO;

public class RhAmountQueryDTO
        extends BaseDTO {

    protected RhAmountQueryDTO(RhAmountQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public RhAmountQueryDTO() {
    }

    public static RhAmountQueryDTOBuilder<?, ?> builder() {
        return new RhAmountQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RhAmountQueryDTO;
    }

    private static final class RhAmountQueryDTOBuilderImpl extends RhAmountQueryDTOBuilder<RhAmountQueryDTO, RhAmountQueryDTOBuilderImpl> {
        private RhAmountQueryDTOBuilderImpl() {
        }

        protected RhAmountQueryDTOBuilderImpl self() {
            return this;
        }

        public RhAmountQueryDTO build() {
            return new RhAmountQueryDTO(this);
        }
    }

    public static abstract class RhAmountQueryDTOBuilder<C extends RhAmountQueryDTO, B extends RhAmountQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

