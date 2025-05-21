
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class RoleMenuBatchCreateDTO extends BaseDTO {

    @ApiModelProperty("角色ID")
     private Long roleId;

    @ApiModelProperty("菜单ID列表")
     private List<Long> menuIdList;



    public void setRoleId(Long roleId) {
        /* 16 */
        this.roleId = roleId;
    }

    public void setMenuIdList(List<Long> menuIdList) {
        this.menuIdList = menuIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleMenuBatchCreateDTO;
    }



    /* 17 */
    protected RoleMenuBatchCreateDTO(RoleMenuBatchCreateDTOBuilder<?, ?> b) {
        super(b);
        this.roleId = b.roleId;
        this.menuIdList = b.menuIdList;
    }

    public static RoleMenuBatchCreateDTOBuilder<?, ?> builder() {
        return new RoleMenuBatchCreateDTOBuilderImpl();
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

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        private List<Long> menuIdList;

        public B menuIdList(List<Long> menuIdList) {
            this.menuIdList = menuIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RoleMenuBatchCreateDTO(Long roleId, List<Long> menuIdList) {
        /* 18 */
        this.roleId = roleId;
        this.menuIdList = menuIdList;

    }


    public RoleMenuBatchCreateDTO() {
    }



    public Long getRoleId() {
        /* 23 */
        return this.roleId;

    }


    public List<Long> getMenuIdList() {
        /* 26 */
        return this.menuIdList;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/dto/RoleMenuBatchCreateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */