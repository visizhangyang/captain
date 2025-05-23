package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class OperateSubVisitApiRequestDTO
        implements Serializable {

    @ApiModelProperty("下级是否可见, 默认不可见, 1-可见, 0-不可见 com.porn.client.common.enums.EnableStatusEnum")
    private Integer subVisit;

    protected OperateSubVisitApiRequestDTO(OperateSubVisitApiRequestDTOBuilder<?, ?> b) {
        this.subVisit = b.subVisit;
    }

    public OperateSubVisitApiRequestDTO(Integer subVisit) {

        this.subVisit = subVisit;

    }

    public OperateSubVisitApiRequestDTO() {
    }

    public static OperateSubVisitApiRequestDTOBuilder<?, ?> builder() {
        return new OperateSubVisitApiRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OperateSubVisitApiRequestDTO;
    }

    public Integer getSubVisit() {

        return this.subVisit;

    }

    public void setSubVisit(Integer subVisit) {

        this.subVisit = subVisit;
    }

    private static final class OperateSubVisitApiRequestDTOBuilderImpl extends OperateSubVisitApiRequestDTOBuilder<OperateSubVisitApiRequestDTO, OperateSubVisitApiRequestDTOBuilderImpl> {
        private OperateSubVisitApiRequestDTOBuilderImpl() {
        }

        protected OperateSubVisitApiRequestDTOBuilderImpl self() {
            return this;
        }

        public OperateSubVisitApiRequestDTO build() {
            return new OperateSubVisitApiRequestDTO(this);
        }
    }

    public static abstract class OperateSubVisitApiRequestDTOBuilder<C extends OperateSubVisitApiRequestDTO, B extends OperateSubVisitApiRequestDTOBuilder<C, B>> {
        private Integer subVisit;

        public B subVisit(Integer subVisit) {
            this.subVisit = subVisit;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

