package com.porn.client.user.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserRoleBatchCreateDTO extends BaseDTO {

    @ApiModelProperty(" 用户ID")
    private Long userId;

    @ApiModelProperty("角色ID列表")
    private List<Long> roleIdList;

    protected UserRoleBatchCreateDTO(UserRoleBatchCreateDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleIdList = b.roleIdList;
    }

    public UserRoleBatchCreateDTO(Long userId, List<Long> roleIdList) {

        this.userId = userId;
        this.roleIdList = roleIdList;

    }

    public UserRoleBatchCreateDTO() {
    }

    public static UserRoleBatchCreateDTOBuilder<?, ?> builder() {
        return new UserRoleBatchCreateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserRoleBatchCreateDTO;
    }

    public Long getUserId() {

        return this.userId;

    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public List<Long> getRoleIdList() {

        return this.roleIdList;

    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    private static final class UserRoleBatchCreateDTOBuilderImpl extends UserRoleBatchCreateDTOBuilder<UserRoleBatchCreateDTO, UserRoleBatchCreateDTOBuilderImpl> {
        private UserRoleBatchCreateDTOBuilderImpl() {
        }

        protected UserRoleBatchCreateDTOBuilderImpl self() {
            return this;
        }

        public UserRoleBatchCreateDTO build() {
            return new UserRoleBatchCreateDTO(this);
        }
    }

    public static abstract class UserRoleBatchCreateDTOBuilder<C extends UserRoleBatchCreateDTO, B extends UserRoleBatchCreateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long userId;
        private List<Long> roleIdList;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

