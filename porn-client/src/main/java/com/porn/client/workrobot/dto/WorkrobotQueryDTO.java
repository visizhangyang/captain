package com.porn.client.workrobot.dto;

import com.porn.client.common.dto.BaseDTO;

public class WorkrobotQueryDTO
        extends BaseDTO {

    protected WorkrobotQueryDTO(WorkrobotQueryDTOBuilder<?, ?> b) {
        super(b);
    }

    public WorkrobotQueryDTO() {
    }

    public static WorkrobotQueryDTOBuilder<?, ?> builder() {
        return new WorkrobotQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkrobotQueryDTO;
    }

    private static final class WorkrobotQueryDTOBuilderImpl extends WorkrobotQueryDTOBuilder<WorkrobotQueryDTO, WorkrobotQueryDTOBuilderImpl> {
        private WorkrobotQueryDTOBuilderImpl() {
        }

        protected WorkrobotQueryDTOBuilderImpl self() {
            return this;
        }

        public WorkrobotQueryDTO build() {
            return new WorkrobotQueryDTO(this);
        }
    }

    public static abstract class WorkrobotQueryDTOBuilder<C extends WorkrobotQueryDTO, B extends WorkrobotQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

