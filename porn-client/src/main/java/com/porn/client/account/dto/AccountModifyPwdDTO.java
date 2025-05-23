package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountModifyPwdDTO extends BaseDTO {

    @ApiModelProperty("类型, AccountModifyPwdTypeEnum")
    private Integer type;

    @ApiModelProperty("是否验证密码")
    private boolean checkPwd;

    @ApiModelProperty("旧密码")
    private String oldPwd;

    @ApiModelProperty("新密码")
    private String newPwd;

    protected AccountModifyPwdDTO(AccountModifyPwdDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.checkPwd = b.checkPwd;
        this.oldPwd = b.oldPwd;
        this.newPwd = b.newPwd;
    }

    public AccountModifyPwdDTO(Integer type, boolean checkPwd, String oldPwd, String newPwd) {

        this.type = type;
        this.checkPwd = checkPwd;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;

    }

    public AccountModifyPwdDTO() {
    }

    public static AccountModifyPwdDTOBuilder<?, ?> builder() {
        return new AccountModifyPwdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountModifyPwdDTO;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isCheckPwd() {

        return this.checkPwd;

    }

    public void setCheckPwd(boolean checkPwd) {
        this.checkPwd = checkPwd;
    }

    public String getOldPwd() {

        return this.oldPwd;

    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {

        return this.newPwd;

    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    private static final class AccountModifyPwdDTOBuilderImpl extends AccountModifyPwdDTOBuilder<AccountModifyPwdDTO, AccountModifyPwdDTOBuilderImpl> {
        private AccountModifyPwdDTOBuilderImpl() {
        }

        protected AccountModifyPwdDTOBuilderImpl self() {
            return this;
        }

        public AccountModifyPwdDTO build() {
            return new AccountModifyPwdDTO(this);
        }
    }

    public static abstract class AccountModifyPwdDTOBuilder<C extends AccountModifyPwdDTO, B extends AccountModifyPwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;
        private boolean checkPwd;
        private String oldPwd;
        private String newPwd;

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B checkPwd(boolean checkPwd) {
            this.checkPwd = checkPwd;
            return self();
        }

        public B oldPwd(String oldPwd) {
            this.oldPwd = oldPwd;
            return self();
        }

        public B newPwd(String newPwd) {
            this.newPwd = newPwd;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

