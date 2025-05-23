package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;

public class UserDeleteDTO
        extends BaseDTO {

    protected UserDeleteDTO(UserDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public UserDeleteDTO() {
    }

    public static UserDeleteDTOBuilder<?, ?> builder() {
        return new UserDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserDeleteDTO;
    }

    private static final class UserDeleteDTOBuilderImpl extends UserDeleteDTOBuilder<UserDeleteDTO, UserDeleteDTOBuilderImpl> {
        private UserDeleteDTOBuilderImpl() {
        }

        protected UserDeleteDTOBuilderImpl self() {
            return this;
        }

        public UserDeleteDTO build() {
            return new UserDeleteDTO(this);
        }
    }

    public static abstract class UserDeleteDTOBuilder<C extends UserDeleteDTO, B extends UserDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

