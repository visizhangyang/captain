package com.porn.client.user.dto;

import com.porn.client.common.dto.AbstractDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserValidatePwdDTO
        extends AbstractDTO {

    @ApiModelProperty("密码")
    private String password;

    protected UserValidatePwdDTO(UserValidatePwdDTOBuilder<?, ?> b) {
        super(b);
        this.password = b.password;
    }

    public UserValidatePwdDTO() {
    }

    public UserValidatePwdDTO(String password) {

        this.password = password;

    }

    public static UserValidatePwdDTOBuilder<?, ?> builder() {
        return new UserValidatePwdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserValidatePwdDTO;
    }

    public String getPassword() {

        return this.password;

    }

    public void setPassword(String password) {

        this.password = password;
    }

    private static final class UserValidatePwdDTOBuilderImpl extends UserValidatePwdDTOBuilder<UserValidatePwdDTO, UserValidatePwdDTOBuilderImpl> {
        private UserValidatePwdDTOBuilderImpl() {
        }

        protected UserValidatePwdDTOBuilderImpl self() {
            return this;
        }

        public UserValidatePwdDTO build() {
            return new UserValidatePwdDTO(this);
        }
    }

    public static abstract class UserValidatePwdDTOBuilder<C extends UserValidatePwdDTO, B extends UserValidatePwdDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        private String password;

        public B password(String password) {
            this.password = password;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

