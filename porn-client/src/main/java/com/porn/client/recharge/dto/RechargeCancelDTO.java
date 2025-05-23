package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BaseDTO;

public class RechargeCancelDTO
        extends BaseDTO {

    protected RechargeCancelDTO(RechargeCancelDTOBuilder<?, ?> b) {
        super(b);
    }

    public RechargeCancelDTO() {
    }

    public static RechargeCancelDTOBuilder<?, ?> builder() {
        return new RechargeCancelDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeCancelDTO;
    }

    private static final class RechargeCancelDTOBuilderImpl extends RechargeCancelDTOBuilder<RechargeCancelDTO, RechargeCancelDTOBuilderImpl> {
        private RechargeCancelDTOBuilderImpl() {
        }

        protected RechargeCancelDTOBuilderImpl self() {
            return this;
        }

        public RechargeCancelDTO build() {
            return new RechargeCancelDTO(this);
        }
    }

    public static abstract class RechargeCancelDTOBuilder<C extends RechargeCancelDTO, B extends RechargeCancelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

