
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

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


    /* 15 */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public void setCaptchaToken(String captchaToken) {
        this.captchaToken = captchaToken;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserLoginDTO;
    }



    /* 16 */
    protected UserLoginDTO(UserLoginDTOBuilder<?, ?> b) {
        super(b);
        this.loginIp = b.loginIp;
        this.name = b.name;
        this.pwd = b.pwd;
        this.captchaCode = b.captchaCode;
        this.captchaToken = b.captchaToken;
    }

    public static UserLoginDTOBuilder<?, ?> builder() {
        return new UserLoginDTOBuilderImpl();
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

        public B loginIp(String loginIp) {
            this.loginIp = loginIp;
            return self();
        }

        private String pwd;
        private String captchaCode;
        private String captchaToken;

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

    public UserLoginDTO(String loginIp, String name, String pwd, String captchaCode, String captchaToken) {
        /* 17 */
        this.loginIp = loginIp;
        this.name = name;
        this.pwd = pwd;
        this.captchaCode = captchaCode;
        this.captchaToken = captchaToken;

    }


    public UserLoginDTO() {
    }



    public String getLoginIp() {
        /* 22 */
        return this.loginIp;

    }


    public String getName() {
        /* 25 */
        return this.name;

    }


    public String getPwd() {
        /* 28 */
        return this.pwd;

    }


    public String getCaptchaCode() {
        /* 31 */
        return this.captchaCode;

    }


    public String getCaptchaToken() {
        /* 34 */
        return this.captchaToken;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserLoginDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */