package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleMenuBatchCreateDTO extends BaseDTO {

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("菜单ID列表")
    private List<Long> menuIdList;

    protected RoleMenuBatchCreateDTO(RoleMenuBatchCreateDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.menuIdList = b.menuIdList;
    }

    public RoleMenuBatchCreateDTO(Long roleId, List<Long> menuIdList) {

        this.roleId = roleId;
        this.menuIdList = menuIdList;

    }

    public RoleMenuBatchCreateDTO() {
    }

    public static RoleMenuBatchCreateDTOBuilder<?, ?> builder() {
        return new RoleMenuBatchCreateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuBatchCreateDTO;
    }

    public Long getRoleId() {

        return this.roleId;

    }

    public void setRoleId(Long roleId) {

        this.roleId = roleId;
    }

    public List<Long> getMenuIdList() {

        return this.menuIdList;

    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }

    private static final class RoleMenuBatchCreateDTOBuilderImpl extends RoleMenuBatchCreateDTOBuilder<RoleMenuBatchCreateDTO, RoleMenuBatchCreateDTOBuilderImpl> {
        private RoleMenuBatchCreateDTOBuilderImpl() {
        }

        protected RoleMenuBatchCreateDTOBuilderImpl self() {
            return this;
        }

        public RoleMenuBatchCreateDTO build() {
            return new RoleMenuBatchCreateDTO(this);
        }
    }

    public static abstract class RoleMenuBatchCreateDTOBuilder<C extends RoleMenuBatchCreateDTO, B extends RoleMenuBatchCreateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long roleId;
        private List<Long> menuIdList;

        public B roleId(Long roleId) {
            this.roleId = roleId;
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

