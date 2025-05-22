
package com.porn.client.user.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class UserRoleBatchCreateDTO extends BaseDTO {

    @ApiModelProperty(" 用户ID")
     private Long userId;

    @ApiModelProperty("角色ID列表")
     private List<Long> roleIdList;



    public void setUserId(Long userId) {
        /* 16 */
        this.userId = userId;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof UserRoleBatchCreateDTO;
    }



    /* 17 */
    protected UserRoleBatchCreateDTO(UserRoleBatchCreateDTOBuilder<?, ?> b) {
        super(b);
        this.userId = b.userId;
        this.roleIdList = b.roleIdList;
    }

    public static UserRoleBatchCreateDTOBuilder<?, ?> builder() {
        return new UserRoleBatchCreateDTOBuilderImpl();
    }

    private static final class UserRoleBatchCreateDTOBuilderImpl extends UserRoleBatchCreateDTOBuilder<UserRoleBatchCreateDTO, UserRoleBatchCreateDTOBuilderImpl> {
        private UserRoleBatchCreateDTOBuilderImpl() {
        }

        protected UserRoleBatchCreateDTOBuilderImpl self() {
            return this;
        }

        public UserRoleBatchCreateDTO build() {
            return new UserRoleBatchCreateDTO(this);
        }
    }

    public static abstract class UserRoleBatchCreateDTOBuilder<C extends UserRoleBatchCreateDTO, B extends UserRoleBatchCreateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long userId;

        public B userId(Long userId) {
            this.userId = userId;
            return self();
        }

        private List<Long> roleIdList;

        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public UserRoleBatchCreateDTO(Long userId, List<Long> roleIdList) {
        /* 18 */
        this.userId = userId;
        this.roleIdList = roleIdList;

    }


    public UserRoleBatchCreateDTO() {
    }



    public Long getUserId() {
        /* 23 */
        return this.userId;

    }


    public List<Long> getRoleIdList() {
        /* 26 */
        return this.roleIdList;

    }

}


