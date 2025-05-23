package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class UserRoleSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("角色ID")
    private Long roleId;

    protected UserRoleSaveOrUpdateDTO(UserRoleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleId = b.roleId;
    }

    public UserRoleSaveOrUpdateDTO(Long userId, Long roleId) {

        this.userId = userId;
        this.roleId = roleId;

    }

    public UserRoleSaveOrUpdateDTO() {
    }

    public static UserRoleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new UserRoleSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserRoleSaveOrUpdateDTO;
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

    private static final class UserRoleSaveOrUpdateDTOBuilderImpl extends UserRoleSaveOrUpdateDTOBuilder<UserRoleSaveOrUpdateDTO, UserRoleSaveOrUpdateDTOBuilderImpl> {
        private UserRoleSaveOrUpdateDTOBuilderImpl() {
        }

        protected UserRoleSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public UserRoleSaveOrUpdateDTO build() {
            return new UserRoleSaveOrUpdateDTO(this);
        }
    }

    public static abstract class UserRoleSaveOrUpdateDTOBuilder<C extends UserRoleSaveOrUpdateDTO, B extends UserRoleSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
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

