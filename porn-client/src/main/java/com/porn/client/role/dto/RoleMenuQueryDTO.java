package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleMenuQueryDTO extends BaseDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色ID列表")
    private List<Long> roleIdList;

    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("菜单ID列表")
    private List<Long> menuIdList;

    protected RoleMenuQueryDTO(RoleMenuQueryDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.roleIdList = b.roleIdList;
        this.menuId = b.menuId;
        this.menuIdList = b.menuIdList;
    }

    public RoleMenuQueryDTO(Long roleId, List<Long> roleIdList, Long menuId, List<Long> menuIdList) {

        this.roleId = roleId;
        this.roleIdList = roleIdList;
        this.menuId = menuId;
        this.menuIdList = menuIdList;

    }

    public RoleMenuQueryDTO() {
    }

    public static RoleMenuQueryDTOBuilder<?, ?> builder() {
        return new RoleMenuQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuQueryDTO;
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

    private static final class RoleMenuQueryDTOBuilderImpl extends RoleMenuQueryDTOBuilder<RoleMenuQueryDTO, RoleMenuQueryDTOBuilderImpl> {
        private RoleMenuQueryDTOBuilderImpl() {
        }

        protected RoleMenuQueryDTOBuilderImpl self() {
            return this;
        }

        public RoleMenuQueryDTO build() {
            return new RoleMenuQueryDTO(this);
        }
    }

    public static abstract class RoleMenuQueryDTOBuilder<C extends RoleMenuQueryDTO, B extends RoleMenuQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
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

