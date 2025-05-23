package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserOfflineDTO
        extends BaseDTO {

    @ApiModelProperty("token")
    private String token;

    protected UserOfflineDTO(UserOfflineDTOBuilder<?, ?> b) {
        super(b);
        this.token = b.token;
    }

    public UserOfflineDTO(String token) {

        this.token = token;

    }

    public UserOfflineDTO() {
    }

    public static UserOfflineDTOBuilder<?, ?> builder() {
        return new UserOfflineDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserOfflineDTO;
    }

    public String getToken() {

        return this.token;

    }

    public void setToken(String token) {

        this.token = token;
    }

    private static final class UserOfflineDTOBuilderImpl extends UserOfflineDTOBuilder<UserOfflineDTO, UserOfflineDTOBuilderImpl> {
        private UserOfflineDTOBuilderImpl() {
        }

        protected UserOfflineDTOBuilderImpl self() {
            return this;
        }

        public UserOfflineDTO build() {
            return new UserOfflineDTO(this);
        }
    }

    public static abstract class UserOfflineDTOBuilder<C extends UserOfflineDTO, B extends UserOfflineDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String token;

        public B token(String token) {
            this.token = token;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

