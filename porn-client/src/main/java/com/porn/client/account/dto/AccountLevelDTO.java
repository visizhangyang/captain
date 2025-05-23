package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountLevelDTO
        extends BaseDTO {

    @ApiModelProperty("账户级别")
    private Integer accountLevel;

    protected AccountLevelDTO(AccountLevelDTOBuilder<?, ?> b) {
        super(b);
        this.accountLevel = b.accountLevel;
    }

    public AccountLevelDTO() {
    }

    public AccountLevelDTO(Integer accountLevel) {

        this.accountLevel = accountLevel;

    }

    public static AccountLevelDTOBuilder<?, ?> builder() {
        return new AccountLevelDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountLevelDTO;
    }

    public Integer getAccountLevel() {

        return this.accountLevel;

    }

    public void setAccountLevel(Integer accountLevel) {

        this.accountLevel = accountLevel;
    }

    private static final class AccountLevelDTOBuilderImpl extends AccountLevelDTOBuilder<AccountLevelDTO, AccountLevelDTOBuilderImpl> {
        private AccountLevelDTOBuilderImpl() {
        }

        protected AccountLevelDTOBuilderImpl self() {
            return this;
        }

        public AccountLevelDTO build() {
            return new AccountLevelDTO(this);
        }
    }

    public static abstract class AccountLevelDTOBuilder<C extends AccountLevelDTO, B extends AccountLevelDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer accountLevel;

        public B accountLevel(Integer accountLevel) {
            this.accountLevel = accountLevel;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

