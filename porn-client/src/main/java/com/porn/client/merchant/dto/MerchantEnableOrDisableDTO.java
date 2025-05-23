package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class MerchantEnableOrDisableDTO
        extends BaseDTO {

    @ApiModelProperty("状态, 1-启用, 0-禁用")
    private Integer status;

    protected MerchantEnableOrDisableDTO(MerchantEnableOrDisableDTOBuilder<?, ?> b) {
        super(b);
        this.status = b.status;
    }

    public MerchantEnableOrDisableDTO(Integer status) {

        this.status = status;

    }

    public MerchantEnableOrDisableDTO() {
    }

    public static MerchantEnableOrDisableDTOBuilder<?, ?> builder() {
        return new MerchantEnableOrDisableDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantEnableOrDisableDTO;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    private static final class MerchantEnableOrDisableDTOBuilderImpl extends MerchantEnableOrDisableDTOBuilder<MerchantEnableOrDisableDTO, MerchantEnableOrDisableDTOBuilderImpl> {
        private MerchantEnableOrDisableDTOBuilderImpl() {
        }

        protected MerchantEnableOrDisableDTOBuilderImpl self() {
            return this;
        }

        public MerchantEnableOrDisableDTO build() {
            return new MerchantEnableOrDisableDTO(this);
        }
    }

    public static abstract class MerchantEnableOrDisableDTOBuilder<C extends MerchantEnableOrDisableDTO, B extends MerchantEnableOrDisableDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

