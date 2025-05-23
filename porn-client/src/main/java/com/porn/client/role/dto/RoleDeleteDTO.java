package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;

public class RoleDeleteDTO
        extends BaseDTO {

    protected RoleDeleteDTO(RoleDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public RoleDeleteDTO() {
    }

    public static RoleDeleteDTOBuilder<?, ?> builder() {
        return new RoleDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleDeleteDTO;
    }

    private static final class RoleDeleteDTOBuilderImpl extends RoleDeleteDTOBuilder<RoleDeleteDTO, RoleDeleteDTOBuilderImpl> {
        private RoleDeleteDTOBuilderImpl() {
        }

        protected RoleDeleteDTOBuilderImpl self() {
            return this;
        }

        public RoleDeleteDTO build() {
            return new RoleDeleteDTO(this);
        }
    }

    public static abstract class RoleDeleteDTOBuilder<C extends RoleDeleteDTO, B extends RoleDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

