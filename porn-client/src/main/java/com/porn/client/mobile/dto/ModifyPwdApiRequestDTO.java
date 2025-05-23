package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ModifyPwdApiRequestDTO implements Serializable {

    @ApiModelProperty("类型, ModifyPwdTypeEnum")
    private Integer type;

    @ApiModelProperty("旧密码")
    private String oldPwd;

    @ApiModelProperty("新密码")
    private String newPwd;

    @ApiModelProperty("验证码token")
    private String captchaToken;

    @ApiModelProperty("验证码")
    private String captcha;

    protected ModifyPwdApiRequestDTO(ModifyPwdApiRequestDTOBuilder<?, ?> b) {
        this.type = b.type;
        this.oldPwd = b.oldPwd;
        this.newPwd = b.newPwd;
        this.captchaToken = b.captchaToken;
        this.captcha = b.captcha;
    }

    public ModifyPwdApiRequestDTO(Integer type, String oldPwd, String newPwd, String captchaToken, String captcha) {

        this.type = type;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.captchaToken = captchaToken;
        this.captcha = captcha;

    }

    public ModifyPwdApiRequestDTO() {
    }

    public static ModifyPwdApiRequestDTOBuilder<?, ?> builder() {
        return new ModifyPwdApiRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ModifyPwdApiRequestDTO;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
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

    private static final class ModifyPwdApiRequestDTOBuilderImpl extends ModifyPwdApiRequestDTOBuilder<ModifyPwdApiRequestDTO, ModifyPwdApiRequestDTOBuilderImpl> {
        private ModifyPwdApiRequestDTOBuilderImpl() {
        }

        protected ModifyPwdApiRequestDTOBuilderImpl self() {
            return this;
        }

        public ModifyPwdApiRequestDTO build() {
            return new ModifyPwdApiRequestDTO(this);
        }
    }

    public static abstract class ModifyPwdApiRequestDTOBuilder<C extends ModifyPwdApiRequestDTO, B extends ModifyPwdApiRequestDTOBuilder<C, B>> {
        private Integer type;
        private String oldPwd;
        private String newPwd;
        private String captchaToken;
        private String captcha;

        public B type(Integer type) {
            this.type = type;
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

        public B captchaToken(String captchaToken) {
            this.captchaToken = captchaToken;
            return self();
        }

        public B captcha(String captcha) {
            this.captcha = captcha;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

