package com.porn.client.config.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class ConfigDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    protected ConfigDeleteDTO(ConfigDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
    }

    public ConfigDeleteDTO() {
    }

    public static ConfigDeleteDTOBuilder<?, ?> builder() {
        return new ConfigDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ConfigDeleteDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    private static final class ConfigDeleteDTOBuilderImpl extends ConfigDeleteDTOBuilder<ConfigDeleteDTO, ConfigDeleteDTOBuilderImpl> {
        private ConfigDeleteDTOBuilderImpl() {
        }

        protected ConfigDeleteDTOBuilderImpl self() {
            return this;
        }

        public ConfigDeleteDTO build() {
            return new ConfigDeleteDTO(this);
        }
    }

    public static abstract class ConfigDeleteDTOBuilder<C extends ConfigDeleteDTO, B extends ConfigDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

