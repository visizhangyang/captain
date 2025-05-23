package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RoleEnableOrDisableDTO
        extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected RoleEnableOrDisableDTO(RoleEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public RoleEnableOrDisableDTO(Integer status) {

        this.status = status;

    }

    public RoleEnableOrDisableDTO() {
    }

    public static RoleEnableOrDisableDTOBuilder<?, ?> builder() {
        return new RoleEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleEnableOrDisableDTO;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    private static final class RoleEnableOrDisableDTOBuilderImpl extends RoleEnableOrDisableDTOBuilder<RoleEnableOrDisableDTO, RoleEnableOrDisableDTOBuilderImpl> {
        private RoleEnableOrDisableDTOBuilderImpl() {
        }

        protected RoleEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public RoleEnableOrDisableDTO build() {
            return new RoleEnableOrDisableDTO(this);
        }
    }

    public static abstract class RoleEnableOrDisableDTOBuilder<C extends RoleEnableOrDisableDTO, B extends RoleEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

