package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleQueryDTO
        extends BaseDTO {

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("id列表")
    private List<Long> roleIdList;

    protected RoleQueryDTO(RoleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.roleIdList = b.roleIdList;
    }

    public RoleQueryDTO(String name, List<Long> roleIdList) {

        this.name = name;
        this.roleIdList = roleIdList;

    }

    public RoleQueryDTO() {
    }

    public static RoleQueryDTOBuilder<?, ?> builder() {
        return new RoleQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleQueryDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {

        this.name = name;
    }

    public List<Long> getRoleIdList() {

        return this.roleIdList;

    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
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
        private List<Long> roleIdList;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B roleIdList(List<Long> roleIdList) {
            this.roleIdList = roleIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

