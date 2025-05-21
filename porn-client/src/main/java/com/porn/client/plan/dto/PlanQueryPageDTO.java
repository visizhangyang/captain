
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class PlanQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊标题")
     private String lkTitle;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;



    public void setLkTitle(String lkTitle) {
        /* 15 */
        this.lkTitle = lkTitle;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PlanQueryPageDTO;
    }



    /* 16 */
    protected PlanQueryPageDTO(PlanQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkTitle = b.lkTitle;
        this.langType = b.langType;
    }

    public static PlanQueryPageDTOBuilder<?, ?> builder() {
        return new PlanQueryPageDTOBuilderImpl();
    }

    private static final class PlanQueryPageDTOBuilderImpl extends PlanQueryPageDTOBuilder<PlanQueryPageDTO, PlanQueryPageDTOBuilderImpl> {
        private PlanQueryPageDTOBuilderImpl() {
        }

        protected PlanQueryPageDTOBuilderImpl self() {
            return this;
        }

        public PlanQueryPageDTO build() {
            return new PlanQueryPageDTO(this);
        }
    }

    public static abstract class PlanQueryPageDTOBuilder<C extends PlanQueryPageDTO, B extends PlanQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String lkTitle;

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

        private Integer langType;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public PlanQueryPageDTO(String lkTitle, Integer langType) {
        /* 17 */
        this.lkTitle = lkTitle;
        this.langType = langType;

    }


    public PlanQueryPageDTO() {
    }



    public String getLkTitle() {
        /* 22 */
        return this.lkTitle;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/dto/PlanQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */