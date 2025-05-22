
package com.porn.client.role.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class RoleQueryDTO
         extends BaseDTO {

    @ApiModelProperty("角色名")
     private String name;

    @ApiModelProperty("id列表")
     private List<Long> roleIdList;



    public void setName(String name) {
        /* 17 */
        this.name = name;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RoleQueryDTO;
    }



    /* 18 */
    protected RoleQueryDTO(RoleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.roleIdList = b.roleIdList;
    }

    public static RoleQueryDTOBuilder<?, ?> builder() {
        return new RoleQueryDTOBuilderImpl();
    }

    private static final class RoleQueryDTOBuilderImpl extends RoleQueryDTOBuilder<RoleQueryDTO, RoleQueryDTOBuilderImpl> {
        private RoleQueryDTOBuilderImpl() {
        }

        protected RoleQueryDTOBuilderImpl self() {
            return this;
        }

        public RoleQueryDTO build() {
            return new RoleQueryDTO(this);
        }
    }

    public static abstract class RoleQueryDTOBuilder<C extends RoleQueryDTO, B extends RoleQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;

        public B name(String name) {
            this.name = name;
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

    public RoleQueryDTO(String name, List<Long> roleIdList) {
        /* 19 */
        this.name = name;
        this.roleIdList = roleIdList;

    }


    public RoleQueryDTO() {
    }



    public String getName() {
        /* 24 */
        return this.name;

    }


    public List<Long> getRoleIdList() {
        /* 27 */
        return this.roleIdList;

    }

}


