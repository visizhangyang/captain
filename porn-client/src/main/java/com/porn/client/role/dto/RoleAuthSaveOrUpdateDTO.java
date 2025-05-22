
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class RoleAuthSaveOrUpdateDTO
         extends BaseDTO
         {
    
    @ApiModelProperty("菜单ID列表")
     private List<Long> menuIdList;

    
    
    public void setMenuIdList(List<Long> menuIdList) {
        /* 16 */
        this.menuIdList = menuIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleAuthSaveOrUpdateDTO;
    }



    /* 17 */
    protected RoleAuthSaveOrUpdateDTO(RoleAuthSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.menuIdList = b.menuIdList;
    }

    public static RoleAuthSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RoleAuthSaveOrUpdateDTOBuilderImpl();
    }

    private static final class RoleAuthSaveOrUpdateDTOBuilderImpl extends RoleAuthSaveOrUpdateDTOBuilder<RoleAuthSaveOrUpdateDTO, RoleAuthSaveOrUpdateDTOBuilderImpl> {
        private RoleAuthSaveOrUpdateDTOBuilderImpl() {
        }

        protected RoleAuthSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RoleAuthSaveOrUpdateDTO build() {
            return new RoleAuthSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RoleAuthSaveOrUpdateDTOBuilder<C extends RoleAuthSaveOrUpdateDTO, B extends RoleAuthSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B menuIdList(List<Long> menuIdList) {
            this.menuIdList = menuIdList;
            return self();
        }

        private List<Long> menuIdList;

        protected abstract B self();

        public abstract C build();

    }

    public RoleAuthSaveOrUpdateDTO(List<Long> menuIdList) {
        /* 18 */
        this.menuIdList = menuIdList;
        
    }

    
    public RoleAuthSaveOrUpdateDTO() {
    }

    
    
    public List<Long> getMenuIdList() {
        /* 23 */
        return this.menuIdList;
        
    }
    
}


