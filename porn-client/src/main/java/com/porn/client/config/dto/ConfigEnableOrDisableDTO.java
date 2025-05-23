package com.porn.client.config.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class ConfigEnableOrDisableDTO
        extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected ConfigEnableOrDisableDTO(ConfigEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public ConfigEnableOrDisableDTO(Integer status) {

        this.status = status;

    }

    public ConfigEnableOrDisableDTO() {
    }

    public static ConfigEnableOrDisableDTOBuilder<?, ?> builder() {
        return new ConfigEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConfigEnableOrDisableDTO;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    private static final class ConfigEnableOrDisableDTOBuilderImpl extends ConfigEnableOrDisableDTOBuilder<ConfigEnableOrDisableDTO, ConfigEnableOrDisableDTOBuilderImpl> {
        private ConfigEnableOrDisableDTOBuilderImpl() {
        }

        protected ConfigEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public ConfigEnableOrDisableDTO build() {
            return new ConfigEnableOrDisableDTO(this);
        }
    }

    public static abstract class ConfigEnableOrDisableDTOBuilder<C extends ConfigEnableOrDisableDTO, B extends ConfigEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

