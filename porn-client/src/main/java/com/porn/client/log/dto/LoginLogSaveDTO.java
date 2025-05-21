
package com.porn.client.log.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.req.ServiceRequest;



 public class LoginLogSaveDTO extends ServiceRequest {

    @ApiModelProperty("用户ID")
     private Long userId;

    @ApiModelProperty("用户名称")
     private String name;

    @ApiModelProperty("登录的IP")
     private String loginIp;



    public void setUserId(Long userId) {
        /* 15 */
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }


    protected boolean canEqual(Object other) {
        return other instanceof LoginLogSaveDTO;
    }



    /* 16 */
    protected LoginLogSaveDTO(LoginLogSaveDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.name = b.name;
        this.loginIp = b.loginIp;
    }

    public static LoginLogSaveDTOBuilder<?, ?> builder() {
        return new LoginLogSaveDTOBuilderImpl();
    }

    private static final class LoginLogSaveDTOBuilderImpl extends LoginLogSaveDTOBuilder<LoginLogSaveDTO, LoginLogSaveDTOBuilderImpl> {
        private LoginLogSaveDTOBuilderImpl() {
        }

        protected LoginLogSaveDTOBuilderImpl self() {
            return this;
        }

        public LoginLogSaveDTO build() {
            return new LoginLogSaveDTO(this);
        }
    }

    public static abstract class LoginLogSaveDTOBuilder<C extends LoginLogSaveDTO, B extends LoginLogSaveDTOBuilder<C, B>> extends ServiceRequest.ServiceRequestBuilder<C, B> {
        private Long userId;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        private String name;
        private String loginIp;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B loginIp(String loginIp) {
            this.loginIp = loginIp;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public LoginLogSaveDTO(Long userId, String name, String loginIp) {
        /* 17 */
        this.userId = userId;
        this.name = name;
        this.loginIp = loginIp;

    }


    public LoginLogSaveDTO() {
    }



    public Long getUserId() {
        /* 22 */
        return this.userId;

    }


    public String getName() {
        /* 25 */
        return this.name;

    }


    public String getLoginIp() {
        /* 28 */
        return this.loginIp;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/log/dto/LoginLogSaveDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */