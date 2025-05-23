package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserEnableOrDisableDTO
        extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected UserEnableOrDisableDTO(UserEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public UserEnableOrDisableDTO(Integer status) {

        this.status = status;

    }

    public UserEnableOrDisableDTO() {
    }

    public static UserEnableOrDisableDTOBuilder<?, ?> builder() {
        return new UserEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserEnableOrDisableDTO;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    private static final class UserEnableOrDisableDTOBuilderImpl extends UserEnableOrDisableDTOBuilder<UserEnableOrDisableDTO, UserEnableOrDisableDTOBuilderImpl> {
        private UserEnableOrDisableDTOBuilderImpl() {
        }

        protected UserEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public UserEnableOrDisableDTO build() {
            return new UserEnableOrDisableDTO(this);
        }
    }

    public static abstract class UserEnableOrDisableDTOBuilder<C extends UserEnableOrDisableDTO, B extends UserEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

