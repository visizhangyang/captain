
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class UserRoleAuthSaveOrUpdateDTO
         extends BaseDTO
         {
    
    @ApiModelProperty("角色ID列表")
     private List<Long> roleIdList;

    
    
    public void setRoleIdList(List<Long> roleIdList) {
        /* 16 */
        this.roleIdList = roleIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserRoleAuthSaveOrUpdateDTO;
    }



    /* 17 */
    protected UserRoleAuthSaveOrUpdateDTO(UserRoleAuthSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.roleIdList = b.roleIdList;
    }

    public static UserRoleAuthSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new UserRoleAuthSaveOrUpdateDTOBuilderImpl();
    }

    private static final class UserRoleAuthSaveOrUpdateDTOBuilderImpl extends UserRoleAuthSaveOrUpdateDTOBuilder<UserRoleAuthSaveOrUpdateDTO, UserRoleAuthSaveOrUpdateDTOBuilderImpl> {
        private UserRoleAuthSaveOrUpdateDTOBuilderImpl() {
        }

        protected UserRoleAuthSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public UserRoleAuthSaveOrUpdateDTO build() {
            return new UserRoleAuthSaveOrUpdateDTO(this);
        }
    }

    public static abstract class UserRoleAuthSaveOrUpdateDTOBuilder<C extends UserRoleAuthSaveOrUpdateDTO, B extends UserRoleAuthSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        private List<Long> roleIdList;

        protected abstract B self();

        public abstract C build();

    }

    public UserRoleAuthSaveOrUpdateDTO(List<Long> roleIdList) {
        /* 18 */
        this.roleIdList = roleIdList;
        
    }

    
    public UserRoleAuthSaveOrUpdateDTO() {
    }

    
    
    public List<Long> getRoleIdList() {
        /* 23 */
        return this.roleIdList;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/dto/UserRoleAuthSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */