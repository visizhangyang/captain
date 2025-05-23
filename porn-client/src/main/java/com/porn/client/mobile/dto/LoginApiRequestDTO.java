package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class LoginApiRequestDTO implements Serializable {

    @ApiModelProperty("账户名称")
    private String name;

    @ApiModelProperty("密码")
    private String loginPwd;

    @ApiModelProperty("验证码token")
    private String captchaToken;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("设备ID")
    private String deviceId;

    public LoginApiRequestDTO(String name, String loginPwd, String captchaToken, String captcha, String deviceId) {

        this.name = name;
        this.loginPwd = loginPwd;
        this.captchaToken = captchaToken;
        this.captcha = captcha;
        this.deviceId = deviceId;

    }

    public LoginApiRequestDTO() {
    }

    public static LoginApiRequestDTOBuilder builder() {
        return new LoginApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof LoginApiRequestDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginPwd() {

        return this.loginPwd;

    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
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

    public static class LoginApiRequestDTOBuilder {
        private String name;
        private String loginPwd;
        private String captchaToken;
        private String captcha;
        private String deviceId;

        public LoginApiRequestDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LoginApiRequestDTOBuilder loginPwd(String loginPwd) {
            this.loginPwd = loginPwd;
            return this;
        }

        public LoginApiRequestDTOBuilder captchaToken(String captchaToken) {
            this.captchaToken = captchaToken;
            return this;
        }

        public LoginApiRequestDTOBuilder captcha(String captcha) {
            this.captcha = captcha;
            return this;
        }

        public LoginApiRequestDTOBuilder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public LoginApiRequestDTO build() {
            return new LoginApiRequestDTO(this.name, this.loginPwd, this.captchaToken, this.captcha, this.deviceId);
        }

    }

}

