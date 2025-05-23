package com.porn.client.common.dto;

import io.swagger.annotations.ApiModelProperty;

public class BasePageDTO
        extends AbstractDTO {

    @ApiModelProperty(value = "序号值", hidden = true)
    private Integer pageIndex;
    @ApiModelProperty(value = "当前页码值", example = "1")
    private Integer pageStart = Integer.valueOf(1);
    @ApiModelProperty(value = "每页数量", example = "20")
    private Integer pageSize = Integer.valueOf(20);

    protected BasePageDTO(BasePageDTOBuilder<?, ?> b) {

        super(b);
        this.pageIndex = b.pageIndex;
        this.pageStart = b.pageStart;
        this.pageSize = b.pageSize;
    }

    public BasePageDTO(Integer pageIndex, Integer pageStart, Integer pageSize) {

        this.pageIndex = pageIndex;
        this.pageStart = pageStart;
        this.pageSize = pageSize;

    }

    public BasePageDTO() {
    }

    public static BasePageDTOBuilder<?, ?> builder() {
        return new BasePageDTOBuilderImpl();
    }

    public Integer getPageIndex() {

        return this.pageIndex;

    }

    public void setPageIndex(Integer pageIndex) {

        this.pageIndex = pageIndex;

    }

    public Integer getPageStart() {

        return this.pageStart;

    }

    public void setPageStart(Integer pageStart) {

        this.pageStart = pageStart;

        calcPageIndex();

    }

    public Integer getPageSize() {

        return this.pageSize;

    }

    public void setPageSize(Integer pageSize) {

        this.pageSize = pageSize;

        calcPageIndex();

    }

    private void calcPageIndex() {

        if (this.pageStart != null && this.pageSize != null)

            this.pageIndex = Integer.valueOf((this.pageStart.intValue() < 1) ? 0 : ((this.pageStart.intValue() - 1) * this.pageSize.intValue()));

    }

    private static final class BasePageDTOBuilderImpl extends BasePageDTOBuilder<BasePageDTO, BasePageDTOBuilderImpl> {
        private BasePageDTOBuilderImpl() {
        }

        protected BasePageDTOBuilderImpl self() {
            return this;
        }

        public BasePageDTO build() {
            return new BasePageDTO(this);
        }
    }

    public static abstract class BasePageDTOBuilder<C extends BasePageDTO, B extends BasePageDTOBuilder<C, B>> extends AbstractDTO.AbstractDTOBuilder<C, B> {
        private Integer pageIndex;
        private Integer pageStart;
        private Integer pageSize;

        public B pageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
            return self();
        }

        public B pageStart(Integer pageStart) {
            this.pageStart = pageStart;
            return self();
        }

        public B pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

