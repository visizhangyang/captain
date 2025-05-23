package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RoleAuthSaveOrUpdateDTO
        extends BaseDTO {

    @ApiModelProperty("菜单ID列表")
    private List<Long> menuIdList;

    protected RoleAuthSaveOrUpdateDTO(RoleAuthSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.menuIdList = b.menuIdList;
    }

    public RoleAuthSaveOrUpdateDTO(List<Long> menuIdList) {

        this.menuIdList = menuIdList;

    }

    public RoleAuthSaveOrUpdateDTO() {
    }

    public static RoleAuthSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RoleAuthSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleAuthSaveOrUpdateDTO;
    }

    public List<Long> getMenuIdList() {

        return this.menuIdList;

    }

    public void setMenuIdList(List<Long> menuIdList) {

        this.menuIdList = menuIdList;
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
        private List<Long> menuIdList;

        public B menuIdList(List<Long> menuIdList) {
            this.menuIdList = menuIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

