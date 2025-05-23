package com.porn.client.recommendapp.dto;

import com.porn.client.common.dto.BaseDTO;

public class RecommendAppDeleteDTO
        extends BaseDTO {

    protected RecommendAppDeleteDTO(RecommendAppDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public RecommendAppDeleteDTO() {
    }

    public static RecommendAppDeleteDTOBuilder<?, ?> builder() {
        return new RecommendAppDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RecommendAppDeleteDTO;
    }

    private static final class RecommendAppDeleteDTOBuilderImpl extends RecommendAppDeleteDTOBuilder<RecommendAppDeleteDTO, RecommendAppDeleteDTOBuilderImpl> {
        private RecommendAppDeleteDTOBuilderImpl() {
        }

        protected RecommendAppDeleteDTOBuilderImpl self() {
            return this;
        }

        public RecommendAppDeleteDTO build() {
            return new RecommendAppDeleteDTO(this);
        }
    }

    public static abstract class RecommendAppDeleteDTOBuilder<C extends RecommendAppDeleteDTO, B extends RecommendAppDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

