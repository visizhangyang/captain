
package com.porn.client.common.dto;
import io.swagger.annotations.ApiModelProperty;










 public class BasePageDTO
         extends AbstractDTO
         {

    @ApiModelProperty(value = "序号值", hidden = true)
     private Integer pageIndex;



    protected BasePageDTO(BasePageDTOBuilder<?, ?> b) {
        /* 17 */
        super(b);
        this.pageIndex = b.pageIndex;
        this.pageStart = b.pageStart;
        this.pageSize = b.pageSize;
    }

    public static BasePageDTOBuilder<?, ?> builder() {
        return new BasePageDTOBuilderImpl();
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

        public B pageIndex(Integer pageIndex) {
            this.pageIndex = pageIndex;
            return self();
        }

        private Integer pageStart;
        private Integer pageSize;

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

    public BasePageDTO(Integer pageIndex, Integer pageStart, Integer pageSize) {
        /* 18 */
        this.pageIndex = pageIndex;
        this.pageStart = pageStart;
        this.pageSize = pageSize;

    }







    @ApiModelProperty(value = "当前页码值", example = "1")
    /* 26 */ private Integer pageStart = Integer.valueOf(1);


    @ApiModelProperty(value = "每页数量", example = "20")
    /* 29 */ private Integer pageSize = Integer.valueOf(20);



    public Integer getPageIndex() {
        /* 32 */
        return this.pageIndex;

    }


    public void setPageIndex(Integer pageIndex) {
        /* 35 */
        this.pageIndex = pageIndex;

    }


    public Integer getPageStart() {
        /* 38 */
        return this.pageStart;

    }


    public void setPageStart(Integer pageStart) {
        /* 41 */
        this.pageStart = pageStart;
        /* 42 */
        calcPageIndex();

    }


    public Integer getPageSize() {
        /* 45 */
        return this.pageSize;

    }


    public void setPageSize(Integer pageSize) {
        /* 48 */
        this.pageSize = pageSize;
        /* 49 */
        calcPageIndex();

    }


    private void calcPageIndex() {
        /* 52 */
        if (this.pageStart != null && this.pageSize != null)
            /* 53 */
            this.pageIndex = Integer.valueOf((this.pageStart.intValue() < 1) ? 0 : ((this.pageStart.intValue() - 1) * this.pageSize.intValue()));

    }



    public BasePageDTO() {
    }

}


