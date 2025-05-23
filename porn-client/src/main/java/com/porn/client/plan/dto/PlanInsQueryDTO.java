package com.porn.client.plan.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected PlanInsQueryDTO(PlanInsQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.planId = b.planId;
        this.status = b.status;
    }

    public PlanInsQueryDTO(Long accountId, List<Long> accountIdList, Long planId, Integer status) {

        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.planId = planId;
        this.status = status;

    }

    public PlanInsQueryDTO() {
    }

    public static PlanInsQueryDTOBuilder<?, ?> builder() {
        return new PlanInsQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanInsQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public Long getPlanId() {

        return this.planId;

    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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
        private Long planId;
        private Integer status;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

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

}

