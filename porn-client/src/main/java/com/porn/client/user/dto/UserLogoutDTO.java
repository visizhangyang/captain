package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserLogoutDTO
        extends BaseDTO {

    @ApiModelProperty("token")
    private String token;

    protected UserLogoutDTO(UserLogoutDTOBuilder<?, ?> b) {
        super(b);
        this.token = b.token;
    }

    public UserLogoutDTO(String token) {

        this.token = token;

    }

    public UserLogoutDTO() {
    }

    public static UserLogoutDTOBuilder<?, ?> builder() {
        return new UserLogoutDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserLogoutDTO;
    }

    public String getToken() {

        return this.token;

    }

    public void setToken(String token) {

        this.token = token;
    }

    private static final class UserLogoutDTOBuilderImpl extends UserLogoutDTOBuilder<UserLogoutDTO, UserLogoutDTOBuilderImpl> {
        private UserLogoutDTOBuilderImpl() {
        }

        protected UserLogoutDTOBuilderImpl self() {
            return this;
        }

        public UserLogoutDTO build() {
            return new UserLogoutDTO(this);
        }
    }

    public static abstract class UserLogoutDTOBuilder<C extends UserLogoutDTO, B extends UserLogoutDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String token;

        public B token(String token) {
            this.token = token;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

