package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserLoginDTO extends BaseDTO {

    @ApiModelProperty("登录的IP")
    private String loginIp;

    @ApiModelProperty("账号")
    private String name;

    @ApiModelProperty("账号")
    private String pwd;

    @ApiModelProperty("验证码")
    private String captchaCode;

    @ApiModelProperty("验证码token")
    private String captchaToken;

    protected UserLoginDTO(UserLoginDTOBuilder<?, ?> b) {
        super(b);
        this.loginIp = b.loginIp;
        this.name = b.name;
        this.pwd = b.pwd;
        this.captchaCode = b.captchaCode;
        this.captchaToken = b.captchaToken;
    }

    public UserLoginDTO(String loginIp, String name, String pwd, String captchaCode, String captchaToken) {

        this.loginIp = loginIp;
        this.name = name;
        this.pwd = pwd;
        this.captchaCode = captchaCode;
        this.captchaToken = captchaToken;

    }

    public UserLoginDTO() {
    }

    public static UserLoginDTOBuilder<?, ?> builder() {
        return new UserLoginDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserLoginDTO;
    }

    public String getLoginIp() {

        return this.loginIp;

    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {

        return this.pwd;

    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCaptchaCode() {

        return this.captchaCode;

    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaToken() {

        return this.captchaToken;

    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }

    private static final class UserLoginDTOBuilderImpl extends UserLoginDTOBuilder<UserLoginDTO, UserLoginDTOBuilderImpl> {
        private UserLoginDTOBuilderImpl() {
        }

        protected UserLoginDTOBuilderImpl self() {
            return this;
        }

        public UserLoginDTO build() {
            return new UserLoginDTO(this);
        }
    }

    public static abstract class UserLoginDTOBuilder<C extends UserLoginDTO, B extends UserLoginDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String loginIp;
        private String name;
        private String pwd;
        private String captchaCode;
        private String captchaToken;

        public B loginIp(String loginIp) {
            this.loginIp = loginIp;
            return self();
        }

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B pwd(String pwd) {
            this.pwd = pwd;
            return self();
        }

        public B captchaCode(String captchaCode) {
            this.captchaCode = captchaCode;
            return self();
        }

        public B captchaToken(String captchaToken) {
            this.captchaToken = captchaToken;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

