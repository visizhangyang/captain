
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class UserRoleSaveOrUpdateDTO extends BaseDTO {

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
        return other instanceof UserRoleSaveOrUpdateDTO;
    }



    /* 16 */
    protected UserRoleSaveOrUpdateDTO(UserRoleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleId = b.roleId;
    }

    public static UserRoleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new UserRoleSaveOrUpdateDTOBuilderImpl();
    }

    private static final class UserRoleSaveOrUpdateDTOBuilderImpl extends UserRoleSaveOrUpdateDTOBuilder<UserRoleSaveOrUpdateDTO, UserRoleSaveOrUpdateDTOBuilderImpl> {
        private UserRoleSaveOrUpdateDTOBuilderImpl() {
        }

        protected UserRoleSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public UserRoleSaveOrUpdateDTO build() {
            return new UserRoleSaveOrUpdateDTO(this);
        }
    }

    public static abstract class UserRoleSaveOrUpdateDTOBuilder<C extends UserRoleSaveOrUpdateDTO, B extends UserRoleSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
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

    public UserRoleSaveOrUpdateDTO(Long userId, Long roleId) {
        /* 17 */
        this.userId = userId;
        this.roleId = roleId;

    }


    public UserRoleSaveOrUpdateDTO() {
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


