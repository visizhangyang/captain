package com.porn.client.log.dto;

import com.porn.client.common.req.ServiceRequest;
import io.swagger.annotations.ApiModelProperty;

public class LoginLogSaveDTO extends ServiceRequest {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("登录的IP")
    private String loginIp;

    protected LoginLogSaveDTO(LoginLogSaveDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.name = b.name;
        this.loginIp = b.loginIp;
    }

    public LoginLogSaveDTO(Long userId, String name, String loginIp) {

        this.userId = userId;
        this.name = name;
        this.loginIp = loginIp;

    }

    public LoginLogSaveDTO() {
    }

    public static LoginLogSaveDTOBuilder<?, ?> builder() {
        return new LoginLogSaveDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof LoginLogSaveDTO;
    }

    public Long getUserId() {

        return this.userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginIp() {

        return this.loginIp;

    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
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
        private String name;
        private String loginIp;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

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

}

