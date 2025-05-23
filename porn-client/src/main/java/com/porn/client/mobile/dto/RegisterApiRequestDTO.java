package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class RegisterApiRequestDTO implements Serializable {
    @ApiModelProperty("账户名称")
    private String name;
    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("密码")
    private String loginPwd;

    @ApiModelProperty("当前账户的父推荐码")
    private String parentPromotionCode;

    @ApiModelProperty("验证码token")
    private String captchaToken;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("设备ID")
    private String deviceId;

    protected RegisterApiRequestDTO(RegisterApiRequestDTOBuilder<?, ?> b) {
        this.name = b.name;
        this.nickName = b.nickName;
        this.loginPwd = b.loginPwd;
        this.parentPromotionCode = b.parentPromotionCode;
        this.captchaToken = b.captchaToken;
        this.captcha = b.captcha;
        this.deviceId = b.deviceId;
    }

    public RegisterApiRequestDTO(String name, String nickName, String loginPwd, String parentPromotionCode, String captchaToken, String captcha, String deviceId) {

        this.name = name;
        this.nickName = nickName;
        this.loginPwd = loginPwd;
        this.parentPromotionCode = parentPromotionCode;
        this.captchaToken = captchaToken;
        this.captcha = captcha;
        this.deviceId = deviceId;

    }

    public RegisterApiRequestDTO() {
    }

    public static RegisterApiRequestDTOBuilder<?, ?> builder() {
        return new RegisterApiRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RegisterApiRequestDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {

        return this.nickName;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginPwd() {

        return this.loginPwd;

    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getParentPromotionCode() {

        return this.parentPromotionCode;

    }

    public void setParentPromotionCode(String parentPromotionCode) {
        this.parentPromotionCode = parentPromotionCode;
    }

    public String getCaptchaToken() {

        return this.captchaToken;

    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }

    public String getCaptcha() {

        return this.captcha;

    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getDeviceId() {

        return this.deviceId;

    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    private static final class RegisterApiRequestDTOBuilderImpl extends RegisterApiRequestDTOBuilder<RegisterApiRequestDTO, RegisterApiRequestDTOBuilderImpl> {
        private RegisterApiRequestDTOBuilderImpl() {
        }

        protected RegisterApiRequestDTOBuilderImpl self() {
            return this;
        }

        public RegisterApiRequestDTO build() {
            return new RegisterApiRequestDTO(this);
        }
    }

    public static abstract class RegisterApiRequestDTOBuilder<C extends RegisterApiRequestDTO, B extends RegisterApiRequestDTOBuilder<C, B>> {
        private String name;
        private String nickName;
        private String loginPwd;
        private String parentPromotionCode;
        private String captchaToken;
        private String captcha;
        private String deviceId;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B nickName(String nickName) {
            this.nickName = nickName;
            return self();
        }

        public B loginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
            return self();
        }

        public B parentPromotionCode(String parentPromotionCode) {
            this.parentPromotionCode = parentPromotionCode;
            return self();
        }

        public B captchaToken(String captchaToken) {
            this.captchaToken = captchaToken;
            return self();
        }

        public B captcha(String captcha) {
            this.captcha = captcha;
            return self();
        }

        public B deviceId(String deviceId) {
            this.deviceId = deviceId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

