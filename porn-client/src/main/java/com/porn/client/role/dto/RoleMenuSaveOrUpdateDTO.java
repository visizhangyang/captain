package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RoleMenuSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("菜单ID")
    private Long menuId;

    protected RoleMenuSaveOrUpdateDTO(RoleMenuSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.menuId = b.menuId;
    }

    public RoleMenuSaveOrUpdateDTO(Long roleId, Long menuId) {

        this.roleId = roleId;
        this.menuId = menuId;

    }

    public RoleMenuSaveOrUpdateDTO() {
    }

    public static RoleMenuSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RoleMenuSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuSaveOrUpdateDTO;
    }

    public Long getRoleId() {

        return this.roleId;

    }

    public void setRoleId(Long roleId) {

        this.roleId = roleId;
    }

    public Long getMenuId() {

        return this.menuId;

    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    private static final class RoleMenuSaveOrUpdateDTOBuilderImpl extends RoleMenuSaveOrUpdateDTOBuilder<RoleMenuSaveOrUpdateDTO, RoleMenuSaveOrUpdateDTOBuilderImpl> {
        private RoleMenuSaveOrUpdateDTOBuilderImpl() {
        }

        protected RoleMenuSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RoleMenuSaveOrUpdateDTO build() {
            return new RoleMenuSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RoleMenuSaveOrUpdateDTOBuilder<C extends RoleMenuSaveOrUpdateDTO, B extends RoleMenuSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long roleId;
        private Long menuId;

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        public B menuId(Long menuId) {
            this.menuId = menuId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

