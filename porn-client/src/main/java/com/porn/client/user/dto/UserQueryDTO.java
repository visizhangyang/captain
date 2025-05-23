package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserQueryDTO
        extends BaseDTO {

    @ApiModelProperty("账户")
    private String name;

    protected UserQueryDTO(UserQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
    }

    public UserQueryDTO(String name) {

        this.name = name;

    }

    public UserQueryDTO() {
    }

    public static UserQueryDTOBuilder<?, ?> builder() {
        return new UserQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserQueryDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {

        this.name = name;
    }

    private static final class UserQueryDTOBuilderImpl extends UserQueryDTOBuilder<UserQueryDTO, UserQueryDTOBuilderImpl> {
        private UserQueryDTOBuilderImpl() {
        }

        protected UserQueryDTOBuilderImpl self() {
            return this;
        }

        public UserQueryDTO build() {
            return new UserQueryDTO(this);
        }
    }

    public static abstract class UserQueryDTOBuilder<C extends UserQueryDTO, B extends UserQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;

        public B name(String name) {
            this.name = name;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

