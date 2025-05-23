package com.porn.client.role.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class RoleQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊匹配名称")
    private String lkName;

    @ApiModelProperty("状态")
    private Integer status;

    protected RoleQueryPageDTO(RoleQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkName = b.lkName;
        this.status = b.status;
    }

    public RoleQueryPageDTO(String lkName, Integer status) {

        this.lkName = lkName;
        this.status = status;

    }

    public RoleQueryPageDTO() {
    }

    public static RoleQueryPageDTOBuilder<?, ?> builder() {
        return new RoleQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleQueryPageDTO;
    }

    public String getLkName() {

        return this.lkName;

    }

    public void setLkName(String lkName) {

        this.lkName = lkName;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final class RoleQueryPageDTOBuilderImpl extends RoleQueryPageDTOBuilder<RoleQueryPageDTO, RoleQueryPageDTOBuilderImpl> {
        private RoleQueryPageDTOBuilderImpl() {
        }

        protected RoleQueryPageDTOBuilderImpl self() {
            return this;
        }

        public RoleQueryPageDTO build() {
            return new RoleQueryPageDTO(this);
        }
    }

    public static abstract class RoleQueryPageDTOBuilder<C extends RoleQueryPageDTO, B extends RoleQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkName;
        private Integer status;

        public B lkName(String lkName) {
            this.lkName = lkName;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

