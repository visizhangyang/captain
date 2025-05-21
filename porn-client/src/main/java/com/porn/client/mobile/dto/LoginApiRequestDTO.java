
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


    /* 15 */
    public void setName(String name) {
        this.name = name;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof LoginApiRequestDTO;
    }



    /* 16 */
    public static LoginApiRequestDTOBuilder builder() {
        return new LoginApiRequestDTOBuilder();
    }

    public static class LoginApiRequestDTOBuilder {
        private String name;
        private String loginPwd;

        public LoginApiRequestDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        private String captchaToken;
        private String captcha;
        private String deviceId;

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

    public LoginApiRequestDTO(String name, String loginPwd, String captchaToken, String captcha, String deviceId) {
        /* 17 */
        this.name = name;
        this.loginPwd = loginPwd;
        this.captchaToken = captchaToken;
        this.captcha = captcha;
        this.deviceId = deviceId;

    }


    public LoginApiRequestDTO() {
    }



    public String getName() {
        /* 22 */
        return this.name;

    }


    public String getLoginPwd() {
        /* 25 */
        return this.loginPwd;

    }


    public String getCaptchaToken() {
        /* 28 */
        return this.captchaToken;

    }


    public String getCaptcha() {
        /* 31 */
        return this.captcha;

    }


    public String getDeviceId() {
        /* 34 */
        return this.deviceId;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/dto/LoginApiRequestDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */