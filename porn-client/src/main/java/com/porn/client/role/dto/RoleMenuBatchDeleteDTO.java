package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleMenuBatchDeleteDTO extends BaseDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色ID列表")
    private List<Long> roleIdList;

    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("菜单ID列表")
    private List<Long> menuIdList;

    protected RoleMenuBatchDeleteDTO(RoleMenuBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.roleIdList = b.roleIdList;
        this.menuId = b.menuId;
        this.menuIdList = b.menuIdList;
    }

    public RoleMenuBatchDeleteDTO(Long roleId, List<Long> roleIdList, Long menuId, List<Long> menuIdList) {

        this.roleId = roleId;
        this.roleIdList = roleIdList;
        this.menuId = menuId;
        this.menuIdList = menuIdList;

    }

    public RoleMenuBatchDeleteDTO() {
    }

    public static RoleMenuBatchDeleteDTOBuilder<?, ?> builder() {
        return new RoleMenuBatchDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuBatchDeleteDTO;
    }

    public Long getRoleId() {

        return this.roleId;

    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getRoleIdList() {

        return this.roleIdList;

    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Long getMenuId() {

        return this.menuId;

    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public List<Long> getMenuIdList() {

        return this.menuIdList;

    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    private static final class RoleMenuBatchDeleteDTOBuilderImpl extends RoleMenuBatchDeleteDTOBuilder<RoleMenuBatchDeleteDTO, RoleMenuBatchDeleteDTOBuilderImpl> {
        private RoleMenuBatchDeleteDTOBuilderImpl() {
        }

        protected RoleMenuBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public RoleMenuBatchDeleteDTO build() {
            return new RoleMenuBatchDeleteDTO(this);
        }
    }

    public static abstract class RoleMenuBatchDeleteDTOBuilder<C extends RoleMenuBatchDeleteDTO, B extends RoleMenuBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long roleId;
        private List<Long> roleIdList;
        private Long menuId;
        private List<Long> menuIdList;

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        public B menuId(Long menuId) {
            this.menuId = menuId;
            return self();
        }

        public B menuIdList(List<Long> menuIdList) {
            this.menuIdList = menuIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

