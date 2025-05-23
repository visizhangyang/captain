package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserRoleAuthSaveOrUpdateDTO
        extends BaseDTO {

    @ApiModelProperty("角色ID列表")
    private List<Long> roleIdList;

    protected UserRoleAuthSaveOrUpdateDTO(UserRoleAuthSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.roleIdList = b.roleIdList;
    }

    public UserRoleAuthSaveOrUpdateDTO(List<Long> roleIdList) {

        this.roleIdList = roleIdList;

    }

    public UserRoleAuthSaveOrUpdateDTO() {
    }

    public static UserRoleAuthSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new UserRoleAuthSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserRoleAuthSaveOrUpdateDTO;
    }

    public List<Long> getRoleIdList() {

        return this.roleIdList;

    }

    public void setRoleIdList(List<Long> roleIdList) {

        this.roleIdList = roleIdList;
    }

    private static final class UserRoleAuthSaveOrUpdateDTOBuilderImpl extends UserRoleAuthSaveOrUpdateDTOBuilder<UserRoleAuthSaveOrUpdateDTO, UserRoleAuthSaveOrUpdateDTOBuilderImpl> {
        private UserRoleAuthSaveOrUpdateDTOBuilderImpl() {
        }

        protected UserRoleAuthSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public UserRoleAuthSaveOrUpdateDTO build() {
            return new UserRoleAuthSaveOrUpdateDTO(this);
        }
    }

    public static abstract class UserRoleAuthSaveOrUpdateDTOBuilder<C extends UserRoleAuthSaveOrUpdateDTO, B extends UserRoleAuthSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> roleIdList;

        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

