package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountChangeNameDTO
        extends BaseDTO {

    @ApiModelProperty("修改后的账号")
    private String name;

    protected AccountChangeNameDTO(AccountChangeNameDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
    }

    public AccountChangeNameDTO(String name) {

        this.name = name;

    }

    public AccountChangeNameDTO() {
    }

    public static AccountChangeNameDTOBuilder<?, ?> builder() {
        return new AccountChangeNameDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountChangeNameDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {

        this.name = name;
    }

    private static final class AccountChangeNameDTOBuilderImpl extends AccountChangeNameDTOBuilder<AccountChangeNameDTO, AccountChangeNameDTOBuilderImpl> {
        private AccountChangeNameDTOBuilderImpl() {
        }

        protected AccountChangeNameDTOBuilderImpl self() {
            return this;
        }

        public AccountChangeNameDTO build() {
            return new AccountChangeNameDTO(this);
        }
    }

    public static abstract class AccountChangeNameDTOBuilder<C extends AccountChangeNameDTO, B extends AccountChangeNameDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;

        public B name(String name) {
            this.name = name;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

