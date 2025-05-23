package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;

public class TurntableQueryDTO
        extends BaseDTO {

    protected TurntableQueryDTO(TurntableQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public TurntableQueryDTO() {
    }

    public static TurntableQueryDTOBuilder<?, ?> builder() {
        return new TurntableQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TurntableQueryDTO;
    }

    private static final class TurntableQueryDTOBuilderImpl extends TurntableQueryDTOBuilder<TurntableQueryDTO, TurntableQueryDTOBuilderImpl> {
        private TurntableQueryDTOBuilderImpl() {
        }

        protected TurntableQueryDTOBuilderImpl self() {
            return this;
        }

        public TurntableQueryDTO build() {
            return new TurntableQueryDTO(this);
        }
    }

    public static abstract class TurntableQueryDTOBuilder<C extends TurntableQueryDTO, B extends TurntableQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

