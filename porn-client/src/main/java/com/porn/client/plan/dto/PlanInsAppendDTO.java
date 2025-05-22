
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;







 public class PlanInsAppendDTO extends BaseDTO {

    @ApiModelProperty("计划实例")
     private Long planId;

    @ApiModelProperty("追加投入")
     private BigDecimal appendInvest;



    public void setPlanId(Long planId) {
        /* 16 */
        this.planId = planId;
    }

    public void setAppendInvest(BigDecimal appendInvest) {
        this.appendInvest = appendInvest;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PlanInsAppendDTO;
    }



    /* 17 */
    protected PlanInsAppendDTO(PlanInsAppendDTOBuilder<?, ?> b) {
        super(b);
        this.planId = b.planId;
        this.appendInvest = b.appendInvest;
    }

    public static PlanInsAppendDTOBuilder<?, ?> builder() {
        return new PlanInsAppendDTOBuilderImpl();
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

        public B planId(Long planId) {
            this.planId = planId;
            return self();
        }

        private BigDecimal appendInvest;

        public B appendInvest(BigDecimal appendInvest) {
            this.appendInvest = appendInvest;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public PlanInsAppendDTO(Long planId, BigDecimal appendInvest) {
        /* 18 */
        this.planId = planId;
        this.appendInvest = appendInvest;

    }


    public PlanInsAppendDTO() {
    }



    public Long getPlanId() {
        /* 23 */
        return this.planId;

    }


    public BigDecimal getAppendInvest() {
        /* 26 */
        return this.appendInvest;

    }

}


