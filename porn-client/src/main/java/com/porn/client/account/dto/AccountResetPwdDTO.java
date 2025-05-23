package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountResetPwdDTO
        extends BaseDTO {

    @ApiModelProperty("重置类型, 0-登录, 1-交易")
    private Integer resetType;

    protected AccountResetPwdDTO(AccountResetPwdDTOBuilder<?, ?> b) {
        super(b);
        this.resetType = b.resetType;
    }

    public AccountResetPwdDTO() {
    }

    public static AccountResetPwdDTOBuilder<?, ?> builder() {
        return new AccountResetPwdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountResetPwdDTO;
    }

    public Integer getResetType() {

        return this.resetType;

    }

    public void setResetType(Integer resetType) {

        this.resetType = resetType;
    }

    private static final class AccountResetPwdDTOBuilderImpl extends AccountResetPwdDTOBuilder<AccountResetPwdDTO, AccountResetPwdDTOBuilderImpl> {
        private AccountResetPwdDTOBuilderImpl() {
        }

        protected AccountResetPwdDTOBuilderImpl self() {
            return this;
        }

        public AccountResetPwdDTO build() {
            return new AccountResetPwdDTO(this);
        }
    }

    public static abstract class AccountResetPwdDTOBuilder<C extends AccountResetPwdDTO, B extends AccountResetPwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer resetType;

        public B resetType(Integer resetType) {
            this.resetType = resetType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

