package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;

public class UserResetPwdDTO
        extends BaseDTO {

    protected UserResetPwdDTO(UserResetPwdDTOBuilder<?, ?> b) {
        super(b);
    }

    public UserResetPwdDTO() {
    }

    public static UserResetPwdDTOBuilder<?, ?> builder() {
        return new UserResetPwdDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserResetPwdDTO;
    }

    private static final class UserResetPwdDTOBuilderImpl extends UserResetPwdDTOBuilder<UserResetPwdDTO, UserResetPwdDTOBuilderImpl> {
        private UserResetPwdDTOBuilderImpl() {
        }

        protected UserResetPwdDTOBuilderImpl self() {
            return this;
        }

        public UserResetPwdDTO build() {
            return new UserResetPwdDTO(this);
        }
    }

    public static abstract class UserResetPwdDTOBuilder<C extends UserResetPwdDTO, B extends UserResetPwdDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

