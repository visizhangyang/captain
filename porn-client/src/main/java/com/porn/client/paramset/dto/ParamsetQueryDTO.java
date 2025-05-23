package com.porn.client.paramset.dto;

import com.porn.client.common.dto.BaseDTO;

public class ParamsetQueryDTO
        extends BaseDTO {

    protected ParamsetQueryDTO(ParamsetQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public ParamsetQueryDTO() {
    }

    public static ParamsetQueryDTOBuilder<?, ?> builder() {
        return new ParamsetQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ParamsetQueryDTO;
    }

    private static final class ParamsetQueryDTOBuilderImpl extends ParamsetQueryDTOBuilder<ParamsetQueryDTO, ParamsetQueryDTOBuilderImpl> {
        private ParamsetQueryDTOBuilderImpl() {
        }

        protected ParamsetQueryDTOBuilderImpl self() {
            return this;
        }

        public ParamsetQueryDTO build() {
            return new ParamsetQueryDTO(this);
        }
    }

    public static abstract class ParamsetQueryDTOBuilder<C extends ParamsetQueryDTO, B extends ParamsetQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

