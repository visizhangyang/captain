
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;
import java.math.BigDecimal;





 public class PlanInsCreateDTO
         implements Serializable
         {

    @ApiModelProperty("计划实例")
     private Long planId;

    @ApiModelProperty("总投入")
     private BigDecimal totalInvest;



    public void setPlanId(Long planId) {
        /* 16 */
        this.planId = planId;
    }

    public void setTotalInvest(BigDecimal totalInvest) {
        this.totalInvest = totalInvest;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PlanInsCreateDTO;
    }



    /* 17 */
    public static PlanInsCreateDTOBuilder builder() {
        return new PlanInsCreateDTOBuilder();
    }

    public static class PlanInsCreateDTOBuilder {
        private Long planId;

        public PlanInsCreateDTOBuilder planId(Long planId) {
            this.planId = planId;
            return this;
        }

        private BigDecimal totalInvest;

        public PlanInsCreateDTOBuilder totalInvest(BigDecimal totalInvest) {
            this.totalInvest = totalInvest;
            return this;
        }

        public PlanInsCreateDTO build() {
            return new PlanInsCreateDTO(this.planId, this.totalInvest);
        }

    }

    public PlanInsCreateDTO(Long planId, BigDecimal totalInvest) {
        /* 18 */
        this.planId = planId;
        this.totalInvest = totalInvest;

    }


    public PlanInsCreateDTO() {
    }



    public Long getPlanId() {
        /* 23 */
        return this.planId;

    }


    public BigDecimal getTotalInvest() {
        /* 26 */
        return this.totalInvest;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/dto/PlanInsCreateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */