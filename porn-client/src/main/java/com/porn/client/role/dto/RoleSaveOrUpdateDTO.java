package com.porn.client.role.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RoleSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("描述")
    private String description;

    protected RoleSaveOrUpdateDTO(RoleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.status = b.status;
        this.description = b.description;
    }

    public RoleSaveOrUpdateDTO(String name, Integer status, String description) {

        this.name = name;
        this.status = status;
        this.description = description;

    }

    public RoleSaveOrUpdateDTO() {
    }

    public static RoleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RoleSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleSaveOrUpdateDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {

        return this.description;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    private static final class RoleSaveOrUpdateDTOBuilderImpl extends RoleSaveOrUpdateDTOBuilder<RoleSaveOrUpdateDTO, RoleSaveOrUpdateDTOBuilderImpl> {
        private RoleSaveOrUpdateDTOBuilderImpl() {
        }

        protected RoleSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RoleSaveOrUpdateDTO build() {
            return new RoleSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RoleSaveOrUpdateDTOBuilder<C extends RoleSaveOrUpdateDTO, B extends RoleSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private Integer status;
        private String description;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B description(String description) {
            this.description = description;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

