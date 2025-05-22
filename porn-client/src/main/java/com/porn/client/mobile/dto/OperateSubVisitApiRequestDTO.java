
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;






 public class OperateSubVisitApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("下级是否可见, 默认不可见, 1-可见, 0-不可见 com.porn.client.common.enums.EnableStatusEnum")
     private Integer subVisit;



    public void setSubVisit(Integer subVisit) {
        /* 15 */
        this.subVisit = subVisit;
    }


    protected boolean canEqual(Object other) {
        return other instanceof OperateSubVisitApiRequestDTO;
    }



    /* 16 */
    protected OperateSubVisitApiRequestDTO(OperateSubVisitApiRequestDTOBuilder<?, ?> b) {
        this.subVisit = b.subVisit;
    }

    public static OperateSubVisitApiRequestDTOBuilder<?, ?> builder() {
        return new OperateSubVisitApiRequestDTOBuilderImpl();
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
        public B subVisit(Integer subVisit) {
            this.subVisit = subVisit;
            return self();
        }

        private Integer subVisit;

        protected abstract B self();

        public abstract C build();

    }

    public OperateSubVisitApiRequestDTO(Integer subVisit) {
        /* 17 */
        this.subVisit = subVisit;

    }


    public OperateSubVisitApiRequestDTO() {
    }



    public Integer getSubVisit() {
        /* 22 */
        return this.subVisit;

    }

}


