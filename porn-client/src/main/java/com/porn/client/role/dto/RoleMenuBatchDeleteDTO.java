
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;

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

    
    /* 16 */
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
        return other instanceof RoleMenuBatchDeleteDTO;
    }



    /* 17 */
    protected RoleMenuBatchDeleteDTO(RoleMenuBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.roleIdList = b.roleIdList;
        this.menuId = b.menuId;
        this.menuIdList = b.menuIdList;
    }

    public static RoleMenuBatchDeleteDTOBuilder<?, ?> builder() {
        return new RoleMenuBatchDeleteDTOBuilderImpl();
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

    public RoleMenuBatchDeleteDTO(Long roleId, List<Long> roleIdList, Long menuId, List<Long> menuIdList) {
        /* 18 */
        this.roleId = roleId;
        this.roleIdList = roleIdList;
        this.menuId = menuId;
        this.menuIdList = menuIdList;
        
    }

    
    public RoleMenuBatchDeleteDTO() {
    }

    
    
    public Long getRoleId() {
        /* 23 */
        return this.roleId;
        
    }

    
    public List<Long> getRoleIdList() {
        /* 26 */
        return this.roleIdList;
        
    }

    
    public Long getMenuId() {
        /* 29 */
        return this.menuId;
        
    }

    
    public List<Long> getMenuIdList() {
        /* 32 */
        return this.menuIdList;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleMenuBatchDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */