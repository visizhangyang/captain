
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserRoleQueryDTO extends BaseDTO {
    
    @ApiModelProperty("用户ID")
     private Long userId;
    
    @ApiModelProperty("角色ID")
     private Long roleId;

    
    
    public void setUserId(Long userId) {
        /* 15 */
        this.userId = userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserRoleQueryDTO;
    }



    /* 16 */
    protected UserRoleQueryDTO(UserRoleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleId = b.roleId;
    }

    public static UserRoleQueryDTOBuilder<?, ?> builder() {
        return new UserRoleQueryDTOBuilderImpl();
    }

    private static final class UserRoleQueryDTOBuilderImpl extends UserRoleQueryDTOBuilder<UserRoleQueryDTO, UserRoleQueryDTOBuilderImpl> {
        private UserRoleQueryDTOBuilderImpl() {
        }

        protected UserRoleQueryDTOBuilderImpl self() {
            return this;
        }

        public UserRoleQueryDTO build() {
            return new UserRoleQueryDTO(this);
        }
    }

    public static abstract class UserRoleQueryDTOBuilder<C extends UserRoleQueryDTO, B extends UserRoleQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long userId;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        private Long roleId;

        public B roleId(Long roleId) {
            this.roleId = roleId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public UserRoleQueryDTO(Long userId, Long roleId) {
        /* 17 */
        this.userId = userId;
        this.roleId = roleId;
        
    }

    
    public UserRoleQueryDTO() {
    }

    
    
    public Long getUserId() {
        /* 22 */
        return this.userId;
        
    }

    
    public Long getRoleId() {
        /* 25 */
        return this.roleId;
        
    }
    
}


