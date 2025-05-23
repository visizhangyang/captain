package com.porn.client.plan.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class PlanQueryDTO extends BaseDTO {

    @ApiModelProperty("模糊标题")
    private String lkTitle;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("语言名称")
    private String langTypeName;

    protected PlanQueryDTO(PlanQueryDTOBuilder<?, ?> b) {
        super(b);
        this.lkTitle = b.lkTitle;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public PlanQueryDTO(String lkTitle, Integer langType, String langTypeName) {

        this.lkTitle = lkTitle;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }

    public PlanQueryDTO() {
    }

    public static PlanQueryDTOBuilder<?, ?> builder() {
        return new PlanQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanQueryDTO;
    }

    public String getLkTitle() {

        return this.lkTitle;

    }

    public void setLkTitle(String lkTitle) {
        this.lkTitle = lkTitle;
    }

    public Integer getLangType() {

        return this.langType;

    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public String getLangTypeName() {

        return this.langTypeName;

    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
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
        private Integer langType;
        private String langTypeName;

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

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

}

