package com.porn.client.autowork.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AutoWorkQueryDTO
        extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    protected AutoWorkQueryDTO(AutoWorkQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
    }

    public AutoWorkQueryDTO(Long accountId) {

        this.accountId = accountId;

    }

    public AutoWorkQueryDTO() {
    }

    public static AutoWorkQueryDTOBuilder<?, ?> builder() {
        return new AutoWorkQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AutoWorkQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    private static final class AutoWorkQueryDTOBuilderImpl extends AutoWorkQueryDTOBuilder<AutoWorkQueryDTO, AutoWorkQueryDTOBuilderImpl> {
        private AutoWorkQueryDTOBuilderImpl() {
        }

        protected AutoWorkQueryDTOBuilderImpl self() {
            return this;
        }

        public AutoWorkQueryDTO build() {
            return new AutoWorkQueryDTO(this);
        }
    }

    public static abstract class AutoWorkQueryDTOBuilder<C extends AutoWorkQueryDTO, B extends AutoWorkQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

