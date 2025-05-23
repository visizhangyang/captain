package com.porn.client.plan.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PlanInsSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;
    @ApiModelProperty("计划ID")
    private Long planId;
    @ApiModelProperty("昨日收益")
    private BigDecimal yesterdayProfit;
    @ApiModelProperty("总收益")
    private BigDecimal totalProfit;

    @ApiModelProperty("总投入")
    private BigDecimal totalInvest;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("状态, PlanInsStatusEnum")
    private Integer status;

    @ApiModelProperty("额外加成")
    private BigDecimal extraBonus;

    protected PlanInsSaveOrUpdateDTO(PlanInsSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.planId = b.planId;
        this.yesterdayProfit = b.yesterdayProfit;
        this.totalProfit = b.totalProfit;
        this.totalInvest = b.totalInvest;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
        this.status = b.status;
        this.extraBonus = b.extraBonus;
    }

    public PlanInsSaveOrUpdateDTO(Long accountId, Long planId, BigDecimal yesterdayProfit, BigDecimal totalProfit, BigDecimal totalInvest, LocalDateTime startTime, LocalDateTime endTime, Integer status, BigDecimal extraBonus) {

        this.accountId = accountId;
        this.planId = planId;
        this.yesterdayProfit = yesterdayProfit;
        this.totalProfit = totalProfit;
        this.totalInvest = totalInvest;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.extraBonus = extraBonus;

    }

    public PlanInsSaveOrUpdateDTO() {
    }

    public static PlanInsSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new PlanInsSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof PlanInsSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getPlanId() {

        return this.planId;

    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public BigDecimal getYesterdayProfit() {

        return this.yesterdayProfit;

    }

    public void setYesterdayProfit(BigDecimal yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public BigDecimal getTotalProfit() {

        return this.totalProfit;

    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BigDecimal getTotalInvest() {

        return this.totalInvest;

    }

    public void setTotalInvest(BigDecimal totalInvest) {
        this.totalInvest = totalInvest;
    }

    public LocalDateTime getStartTime() {

        return this.startTime;

    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {

        return this.endTime;

    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getExtraBonus() {

        return this.extraBonus;

    }

    public void setExtraBonus(BigDecimal extraBonus) {
        this.extraBonus = extraBonus;
    }

    private static final class PlanInsSaveOrUpdateDTOBuilderImpl extends PlanInsSaveOrUpdateDTOBuilder<PlanInsSaveOrUpdateDTO, PlanInsSaveOrUpdateDTOBuilderImpl> {
        private PlanInsSaveOrUpdateDTOBuilderImpl() {
        }

        protected PlanInsSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public PlanInsSaveOrUpdateDTO build() {
            return new PlanInsSaveOrUpdateDTO(this);
        }
    }

    public static abstract class PlanInsSaveOrUpdateDTOBuilder<C extends PlanInsSaveOrUpdateDTO, B extends PlanInsSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Long planId;
        private BigDecimal yesterdayProfit;
        private BigDecimal totalProfit;
        private BigDecimal totalInvest;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer status;
        private BigDecimal extraBonus;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B planId(Long planId) {
            this.planId = planId;
            return self();
        }

        public B yesterdayProfit(BigDecimal yesterdayProfit) {
            this.yesterdayProfit = yesterdayProfit;
            return self();
        }

        public B totalProfit(BigDecimal totalProfit) {
            this.totalProfit = totalProfit;
            return self();
        }

        public B totalInvest(BigDecimal totalInvest) {
            this.totalInvest = totalInvest;
            return self();
        }

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        public B endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B extraBonus(BigDecimal extraBonus) {
            this.extraBonus = extraBonus;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

