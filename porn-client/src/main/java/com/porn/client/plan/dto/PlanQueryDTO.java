
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;




 public class PlanQueryDTO extends BaseDTO {

    @ApiModelProperty("模糊标题")
     private String lkTitle;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("语言名称")
     private String langTypeName;


    /* 15 */
    public void setLkTitle(String lkTitle) {
        this.lkTitle = lkTitle;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PlanQueryDTO;
    }



    /* 16 */
    protected PlanQueryDTO(PlanQueryDTOBuilder<?, ?> b) {
        super(b);
        this.lkTitle = b.lkTitle;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public static PlanQueryDTOBuilder<?, ?> builder() {
        return new PlanQueryDTOBuilderImpl();
    }

    private static final class PlanQueryDTOBuilderImpl extends PlanQueryDTOBuilder<PlanQueryDTO, PlanQueryDTOBuilderImpl> {
        private PlanQueryDTOBuilderImpl() {
        }

        protected PlanQueryDTOBuilderImpl self() {
            return this;
        }

        public PlanQueryDTO build() {
            return new PlanQueryDTO(this);
        }
    }

    public static abstract class PlanQueryDTOBuilder<C extends PlanQueryDTO, B extends PlanQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String lkTitle;

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

        private Integer langType;
        private String langTypeName;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B langTypeName(String langTypeName) {
            this.langTypeName = langTypeName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public PlanQueryDTO(String lkTitle, Integer langType, String langTypeName) {
        /* 17 */
        this.lkTitle = lkTitle;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }


    public PlanQueryDTO() {
    }



    public String getLkTitle() {
        /* 22 */
        return this.lkTitle;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }


    public String getLangTypeName() {
        /* 28 */
        return this.langTypeName;

    }

}


