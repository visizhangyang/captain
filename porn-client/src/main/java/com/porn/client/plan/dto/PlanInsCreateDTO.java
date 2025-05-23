package com.porn.client.plan.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class PlanInsCreateDTO
        implements Serializable {

    @ApiModelProperty("计划实例")
    private Long planId;

    @ApiModelProperty("总投入")
    private BigDecimal totalInvest;

    public PlanInsCreateDTO(Long planId, BigDecimal totalInvest) {

        this.planId = planId;
        this.totalInvest = totalInvest;

    }

    public PlanInsCreateDTO() {
    }

    public static PlanInsCreateDTOBuilder builder() {
        return new PlanInsCreateDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanInsCreateDTO;
    }

    public Long getPlanId() {

        return this.planId;

    }

    public void setPlanId(Long planId) {

        this.planId = planId;
    }

    public BigDecimal getTotalInvest() {

        return this.totalInvest;

    }

    public void setTotalInvest(BigDecimal totalInvest) {
        this.totalInvest = totalInvest;
    }

    public static class PlanInsCreateDTOBuilder {
        private Long planId;
        private BigDecimal totalInvest;

        public PlanInsCreateDTOBuilder planId(Long planId) {
            this.planId = planId;
            return this;
        }

        public PlanInsCreateDTOBuilder totalInvest(BigDecimal totalInvest) {
            this.totalInvest = totalInvest;
            return this;
        }

        public PlanInsCreateDTO build() {
            return new PlanInsCreateDTO(this.planId, this.totalInvest);
        }

    }

}

