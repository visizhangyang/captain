
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;

import java.util.List;




 public class UserRoleBatchDeleteDTO extends BaseDTO {
    
    @ApiModelProperty("用户ID")
     private Long userId;
    
    @ApiModelProperty("角色ID列表")
     private List<Long> roleIdList;
    
    @ApiModelProperty("角色ID")
     private Long roleId;
    
    @ApiModelProperty("用户ID列表")
     private List<Long> userIdList;

    
    /* 16 */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserRoleBatchDeleteDTO;
    }



    /* 17 */
    protected UserRoleBatchDeleteDTO(UserRoleBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleIdList = b.roleIdList;
        this.roleId = b.roleId;
        this.userIdList = b.userIdList;
    }

    public static UserRoleBatchDeleteDTOBuilder<?, ?> builder() {
        return new UserRoleBatchDeleteDTOBuilderImpl();
    }

    private static final class UserRoleBatchDeleteDTOBuilderImpl extends UserRoleBatchDeleteDTOBuilder<UserRoleBatchDeleteDTO, UserRoleBatchDeleteDTOBuilderImpl> {
        private UserRoleBatchDeleteDTOBuilderImpl() {
        }

        protected UserRoleBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public UserRoleBatchDeleteDTO build() {
            return new UserRoleBatchDeleteDTO(this);
        }
    }

    public static abstract class UserRoleBatchDeleteDTOBuilder<C extends UserRoleBatchDeleteDTO, B extends UserRoleBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long userId;
        private List<Long> roleIdList;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        private Long roleId;
        private List<Long> userIdList;

        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        public B userIdList(List<Long> userIdList) {
            this.userIdList = userIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public UserRoleBatchDeleteDTO(Long userId, List<Long> roleIdList, Long roleId, List<Long> userIdList) {
        /* 18 */
        this.userId = userId;
        this.roleIdList = roleIdList;
        this.roleId = roleId;
        this.userIdList = userIdList;
        
    }

    
    public UserRoleBatchDeleteDTO() {
    }

    
    
    public Long getUserId() {
        /* 23 */
        return this.userId;
        
    }

    
    public List<Long> getRoleIdList() {
        /* 26 */
        return this.roleIdList;
        
    }

    
    public Long getRoleId() {
        /* 29 */
        return this.roleId;
        
    }

    
    public List<Long> getUserIdList() {
        /* 32 */
        return this.userIdList;
        
    }
    
}


