package com.porn.client.user.dto;

import com.porn.client.common.dto.AbstractDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserNewPwdDTO
        extends AbstractDTO {

    @ApiModelProperty("密码")
    private String pwd;

    @ApiModelProperty("新密码")
    private String newPwd;

    protected UserNewPwdDTO(UserNewPwdDTOBuilder<?, ?> b) {
        super(b);
        this.pwd = b.pwd;
        this.newPwd = b.newPwd;
    }

    public UserNewPwdDTO() {
    }

    public UserNewPwdDTO(String pwd, String newPwd) {

        this.pwd = pwd;
        this.newPwd = newPwd;

    }

    public static UserNewPwdDTOBuilder<?, ?> builder() {
        return new UserNewPwdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserNewPwdDTO;
    }

    public String getPwd() {

        return this.pwd;

    }

    public void setPwd(String pwd) {

        this.pwd = pwd;
    }

    public String getNewPwd() {

        return this.newPwd;

    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    private static final class UserNewPwdDTOBuilderImpl extends UserNewPwdDTOBuilder<UserNewPwdDTO, UserNewPwdDTOBuilderImpl> {
        private UserNewPwdDTOBuilderImpl() {
        }

        protected UserNewPwdDTOBuilderImpl self() {
            return this;
        }

        public UserNewPwdDTO build() {
            return new UserNewPwdDTO(this);
        }
    }

    public static abstract class UserNewPwdDTOBuilder<C extends UserNewPwdDTO, B extends UserNewPwdDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        private String pwd;
        private String newPwd;

        public B pwd(String pwd) {
            this.pwd = pwd;
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

