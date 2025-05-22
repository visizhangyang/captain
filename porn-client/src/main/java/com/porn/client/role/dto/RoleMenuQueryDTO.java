
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;

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

    
    /* 17 */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuQueryDTO;
    }



    /* 18 */
    protected RoleMenuQueryDTO(RoleMenuQueryDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.roleIdList = b.roleIdList;
        this.menuId = b.menuId;
        this.menuIdList = b.menuIdList;
    }

    public static RoleMenuQueryDTOBuilder<?, ?> builder() {
        return new RoleMenuQueryDTOBuilderImpl();
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

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        private Long menuId;
        private List<Long> menuIdList;

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

    public RoleMenuQueryDTO(Long roleId, List<Long> roleIdList, Long menuId, List<Long> menuIdList) {
        /* 19 */
        this.roleId = roleId;
        this.roleIdList = roleIdList;
        this.menuId = menuId;
        this.menuIdList = menuIdList;
        
    }

    
    public RoleMenuQueryDTO() {
    }

    
    
    public Long getRoleId() {
        /* 24 */
        return this.roleId;
        
    }

    
    public List<Long> getRoleIdList() {
        /* 27 */
        return this.roleIdList;
        
    }

    
    public Long getMenuId() {
        /* 30 */
        return this.menuId;
        
    }

    
    public List<Long> getMenuIdList() {
        /* 33 */
        return this.menuIdList;
        
    }
    
}


