package com.porn.client.plan.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class PlanInsAppendDTO extends BaseDTO {

    @ApiModelProperty("计划实例")
    private Long planId;

    @ApiModelProperty("追加投入")
    private BigDecimal appendInvest;

    protected PlanInsAppendDTO(PlanInsAppendDTOBuilder<?, ?> b) {
        super(b);
        this.planId = b.planId;
        this.appendInvest = b.appendInvest;
    }

    public PlanInsAppendDTO(Long planId, BigDecimal appendInvest) {

        this.planId = planId;
        this.appendInvest = appendInvest;

    }

    public PlanInsAppendDTO() {
    }

    public static PlanInsAppendDTOBuilder<?, ?> builder() {
        return new PlanInsAppendDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanInsAppendDTO;
    }

    public Long getPlanId() {

        return this.planId;

    }

    public void setPlanId(Long planId) {

        this.planId = planId;
    }

    public BigDecimal getAppendInvest() {

        return this.appendInvest;

    }

    public void setAppendInvest(BigDecimal appendInvest) {
        this.appendInvest = appendInvest;
    }

    private static final class PlanInsAppendDTOBuilderImpl extends PlanInsAppendDTOBuilder<PlanInsAppendDTO, PlanInsAppendDTOBuilderImpl> {
        private PlanInsAppendDTOBuilderImpl() {
        }

        protected PlanInsAppendDTOBuilderImpl self() {
            return this;
        }

        public PlanInsAppendDTO build() {
            return new PlanInsAppendDTO(this);
        }
    }

    public static abstract class PlanInsAppendDTOBuilder<C extends PlanInsAppendDTO, B extends PlanInsAppendDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long planId;
        private BigDecimal appendInvest;

        public B planId(Long planId) {
            this.planId = planId;
            return self();
        }

        public B appendInvest(BigDecimal appendInvest) {
            this.appendInvest = appendInvest;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

