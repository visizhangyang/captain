
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RoleMenuSaveOrUpdateDTO extends BaseDTO {
    
    @ApiModelProperty("角色ID")
     private Long roleId;
    
    @ApiModelProperty("菜单ID")
     private Long menuId;

    
    
    public void setRoleId(Long roleId) {
        /* 15 */
        this.roleId = roleId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuSaveOrUpdateDTO;
    }



    /* 16 */
    protected RoleMenuSaveOrUpdateDTO(RoleMenuSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.menuId = b.menuId;
    }

    public static RoleMenuSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RoleMenuSaveOrUpdateDTOBuilderImpl();
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

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        private Long menuId;

        public B menuId(Long menuId) {
            this.menuId = menuId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RoleMenuSaveOrUpdateDTO(Long roleId, Long menuId) {
        /* 17 */
        this.roleId = roleId;
        this.menuId = menuId;
        
    }

    
    public RoleMenuSaveOrUpdateDTO() {
    }

    
    
    public Long getRoleId() {
        /* 22 */
        return this.roleId;
        
    }

    
    public Long getMenuId() {
        /* 25 */
        return this.menuId;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleMenuSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */