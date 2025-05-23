package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserInfoDTO
        extends BaseDTO {

    @ApiModelProperty("token")
    private String token;

    protected UserInfoDTO(UserInfoDTOBuilder<?, ?> b) {
        super(b);
        this.token = b.token;
    }

    public UserInfoDTO(String token) {

        this.token = token;

    }

    public UserInfoDTO() {
    }

    public static UserInfoDTOBuilder<?, ?> builder() {
        return new UserInfoDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserInfoDTO;
    }

    public String getToken() {

        return this.token;

    }

    public void setToken(String token) {

        this.token = token;
    }

    private static final class UserInfoDTOBuilderImpl extends UserInfoDTOBuilder<UserInfoDTO, UserInfoDTOBuilderImpl> {
        private UserInfoDTOBuilderImpl() {
        }

        protected UserInfoDTOBuilderImpl self() {
            return this;
        }

        public UserInfoDTO build() {
            return new UserInfoDTO(this);
        }
    }

    public static abstract class UserInfoDTOBuilder<C extends UserInfoDTO, B extends UserInfoDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String token;

        public B token(String token) {
            this.token = token;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

