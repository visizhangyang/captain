package com.porn.client.plan.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class PlanQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("模糊标题")
    private String lkTitle;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    protected PlanQueryPageDTO(PlanQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.lkTitle = b.lkTitle;
        this.langType = b.langType;
    }

    public PlanQueryPageDTO(String lkTitle, Integer langType) {

        this.lkTitle = lkTitle;
        this.langType = langType;

    }

    public PlanQueryPageDTO() {
    }

    public static PlanQueryPageDTOBuilder<?, ?> builder() {
        return new PlanQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanQueryPageDTO;
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
        private Integer langType;

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

