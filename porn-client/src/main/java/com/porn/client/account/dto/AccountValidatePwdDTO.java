package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountValidatePwdDTO extends BaseDTO {

    @ApiModelProperty("验证类型, AccountValidateTypeEnum")
    private Integer type;

    @ApiModelProperty("密码")
    private String pwd;

    protected AccountValidatePwdDTO(AccountValidatePwdDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.pwd = b.pwd;
    }

    public AccountValidatePwdDTO(Integer type, String pwd) {

        this.type = type;
        this.pwd = pwd;

    }

    public AccountValidatePwdDTO() {
    }

    public static AccountValidatePwdDTOBuilder<?, ?> builder() {
        return new AccountValidatePwdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountValidatePwdDTO;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {

        this.type = type;
    }

    public String getPwd() {

        return this.pwd;

    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    private static final class AccountValidatePwdDTOBuilderImpl extends AccountValidatePwdDTOBuilder<AccountValidatePwdDTO, AccountValidatePwdDTOBuilderImpl> {
        private AccountValidatePwdDTOBuilderImpl() {
        }

        protected AccountValidatePwdDTOBuilderImpl self() {
            return this;
        }

        public AccountValidatePwdDTO build() {
            return new AccountValidatePwdDTO(this);
        }
    }

    public static abstract class AccountValidatePwdDTOBuilder<C extends AccountValidatePwdDTO, B extends AccountValidatePwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;
        private String pwd;

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B pwd(String pwd) {
            this.pwd = pwd;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

