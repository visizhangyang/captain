package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserRoleQueryDTO extends BaseDTO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("角色ID")
    private Long roleId;

    protected UserRoleQueryDTO(UserRoleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleId = b.roleId;
    }

    public UserRoleQueryDTO(Long userId, Long roleId) {

        this.userId = userId;
        this.roleId = roleId;

    }

    public UserRoleQueryDTO() {
    }

    public static UserRoleQueryDTOBuilder<?, ?> builder() {
        return new UserRoleQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserRoleQueryDTO;
    }

    public Long getUserId() {

        return this.userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public Long getRoleId() {

        return this.roleId;

    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    private static final class UserRoleQueryDTOBuilderImpl extends UserRoleQueryDTOBuilder<UserRoleQueryDTO, UserRoleQueryDTOBuilderImpl> {
        private UserRoleQueryDTOBuilderImpl() {
        }

        protected UserRoleQueryDTOBuilderImpl self() {
            return this;
        }

        public UserRoleQueryDTO build() {
            return new UserRoleQueryDTO(this);
        }
    }

    public static abstract class UserRoleQueryDTOBuilder<C extends UserRoleQueryDTO, B extends UserRoleQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long userId;
        private Long roleId;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

