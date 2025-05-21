
package com.porn.client.plan.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;


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


    /* 17 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public void setYesterdayProfit(BigDecimal yesterdayProfit) {
        this.yesterdayProfit = yesterdayProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void setTotalInvest(BigDecimal totalInvest) {
        this.totalInvest = totalInvest;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setExtraBonus(BigDecimal extraBonus) {
        this.extraBonus = extraBonus;
    }


    protected boolean canEqual(Object other) {
        return other instanceof PlanInsSaveOrUpdateDTO;
    }



    /* 18 */
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

    public static PlanInsSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new PlanInsSaveOrUpdateDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private BigDecimal totalInvest;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private Integer status;
        private BigDecimal extraBonus;

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

    public PlanInsSaveOrUpdateDTO(Long accountId, Long planId, BigDecimal yesterdayProfit, BigDecimal totalProfit, BigDecimal totalInvest, LocalDateTime startTime, LocalDateTime endTime, Integer status, BigDecimal extraBonus) {
        /* 19 */
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



    public Long getAccountId() {
        /* 24 */
        return this.accountId;

    }


    public Long getPlanId() {
        /* 27 */
        return this.planId;

    }


    public BigDecimal getYesterdayProfit() {
        /* 30 */
        return this.yesterdayProfit;

    }


    public BigDecimal getTotalProfit() {
        /* 33 */
        return this.totalProfit;

    }


    public BigDecimal getTotalInvest() {
        /* 36 */
        return this.totalInvest;

    }


    public LocalDateTime getStartTime() {
        /* 39 */
        return this.startTime;

    }


    public LocalDateTime getEndTime() {
        /* 42 */
        return this.endTime;

    }


    public Integer getStatus() {
        /* 45 */
        return this.status;

    }


    public BigDecimal getExtraBonus() {
        /* 48 */
        return this.extraBonus;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/plan/dto/PlanInsSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */