
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;





 public class PlanInsQueryDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("账户ID列表")
     private List<Long> accountIdList;

    @ApiModelProperty("计划ID")
     private Long planId;

    @ApiModelProperty("状态, PlanInsStatusEnum")
     private Integer status;


    /* 17 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PlanInsQueryDTO;
    }



    /* 18 */
    protected PlanInsQueryDTO(PlanInsQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.planId = b.planId;
        this.status = b.status;
    }

    public static PlanInsQueryDTOBuilder<?, ?> builder() {
        return new PlanInsQueryDTOBuilderImpl();
    }

    private static final class PlanInsQueryDTOBuilderImpl extends PlanInsQueryDTOBuilder<PlanInsQueryDTO, PlanInsQueryDTOBuilderImpl> {
        private PlanInsQueryDTOBuilderImpl() {
        }

        protected PlanInsQueryDTOBuilderImpl self() {
            return this;
        }

        public PlanInsQueryDTO build() {
            return new PlanInsQueryDTO(this);
        }
    }

    public static abstract class PlanInsQueryDTOBuilder<C extends PlanInsQueryDTO, B extends PlanInsQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private List<Long> accountIdList;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Long planId;
        private Integer status;

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        public B planId(Long planId) {
            this.planId = planId;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public PlanInsQueryDTO(Long accountId, List<Long> accountIdList, Long planId, Integer status) {
        /* 19 */
        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.planId = planId;
        this.status = status;

    }


    public PlanInsQueryDTO() {
    }



    public Long getAccountId() {
        /* 24 */
        return this.accountId;

    }


    public List<Long> getAccountIdList() {
        /* 27 */
        return this.accountIdList;

    }


    public Long getPlanId() {
        /* 30 */
        return this.planId;

    }


    public Integer getStatus() {
        /* 33 */
        return this.status;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/dto/PlanInsQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */