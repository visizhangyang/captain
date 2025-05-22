
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


    /* 15 */
    public void setName(String name) {
        this.name = name;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setParentPromotionCode(String parentPromotionCode) {
        this.parentPromotionCode = parentPromotionCode;
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
        return other instanceof RegisterApiRequestDTO;
    }



    /* 16 */
    protected RegisterApiRequestDTO(RegisterApiRequestDTOBuilder<?, ?> b) {
        this.name = b.name;
        this.nickName = b.nickName;
        this.loginPwd = b.loginPwd;
        this.parentPromotionCode = b.parentPromotionCode;
        this.captchaToken = b.captchaToken;
        this.captcha = b.captcha;
        this.deviceId = b.deviceId;
    }

    public static RegisterApiRequestDTOBuilder<?, ?> builder() {
        return new RegisterApiRequestDTOBuilderImpl();
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

        public B name(String name) {
            this.name = name;
            return self();
        }

        private String parentPromotionCode;
        private String captchaToken;
        private String captcha;
        private String deviceId;

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

    public RegisterApiRequestDTO(String name, String nickName, String loginPwd, String parentPromotionCode, String captchaToken, String captcha, String deviceId) {
        /* 17 */
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



    public String getName() {
        /* 22 */
        return this.name;

    }


    public String getNickName() {
        /* 25 */
        return this.nickName;

    }


    public String getLoginPwd() {
        /* 28 */
        return this.loginPwd;

    }


    public String getParentPromotionCode() {
        /* 31 */
        return this.parentPromotionCode;

    }


    public String getCaptchaToken() {
        /* 34 */
        return this.captchaToken;

    }


    public String getCaptcha() {
        /* 37 */
        return this.captcha;

    }


    public String getDeviceId() {
        /* 40 */
        return this.deviceId;

    }
}


