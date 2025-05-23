package com.porn.client.plan.dto;

import com.porn.client.common.dto.BaseDTO;

public class PlanDeleteDTO
        extends BaseDTO {

    protected PlanDeleteDTO(PlanDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public PlanDeleteDTO() {
    }

    public static PlanDeleteDTOBuilder<?, ?> builder() {
        return new PlanDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanDeleteDTO;
    }

    private static final class PlanDeleteDTOBuilderImpl extends PlanDeleteDTOBuilder<PlanDeleteDTO, PlanDeleteDTOBuilderImpl> {
        private PlanDeleteDTOBuilderImpl() {
        }

        protected PlanDeleteDTOBuilderImpl self() {
            return this;
        }

        public PlanDeleteDTO build() {
            return new PlanDeleteDTO(this);
        }
    }

    public static abstract class PlanDeleteDTOBuilder<C extends PlanDeleteDTO, B extends PlanDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

