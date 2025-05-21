
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

    
    /* 15 */
    public void setType(Integer type) {
        this.type = type;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ModifyPwdApiRequestDTO;
    }



    /* 16 */
    protected ModifyPwdApiRequestDTO(ModifyPwdApiRequestDTOBuilder<?, ?> b) {
        this.type = b.type;
        this.oldPwd = b.oldPwd;
        this.newPwd = b.newPwd;
        this.captchaToken = b.captchaToken;
        this.captcha = b.captcha;
    }

    public static ModifyPwdApiRequestDTOBuilder<?, ?> builder() {
        return new ModifyPwdApiRequestDTOBuilderImpl();
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

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        private String newPwd;
        private String captchaToken;
        private String captcha;

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

    public ModifyPwdApiRequestDTO(Integer type, String oldPwd, String newPwd, String captchaToken, String captcha) {
        /* 17 */
        this.type = type;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.captchaToken = captchaToken;
        this.captcha = captcha;
        
    }

    
    public ModifyPwdApiRequestDTO() {
    }

    
    
    public Integer getType() {
        /* 22 */
        return this.type;
        
    }

    
    public String getOldPwd() {
        /* 25 */
        return this.oldPwd;
        
    }

    
    public String getNewPwd() {
        /* 28 */
        return this.newPwd;
        
    }

    
    public String getCaptchaToken() {
        /* 31 */
        return this.captchaToken;
        
    }

    
    public String getCaptcha() {
        /* 34 */
        return this.captcha;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/dto/ModifyPwdApiRequestDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */