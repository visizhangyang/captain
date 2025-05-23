package com.porn.client.workrobot.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class WorkrobotSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("最小搬砖金额")
    private BigDecimal minWorkAmount;
    @ApiModelProperty("最大搬砖金额")
    private BigDecimal maxWorkAmount;
    @ApiModelProperty("搬砖时间范围(小)")
    private String minWorkTime;
    @ApiModelProperty("搬砖时间范围(大)")
    private String maxWorkTime;

    @ApiModelProperty("订单数量")
    private Integer orderCount;

    @ApiModelProperty("放款时间(小)")
    private Integer minLoanTime;

    @ApiModelProperty("放款时间(大)")
    private Integer maxLoanTime;

    @ApiModelProperty("完成时间(小)")
    private Integer minCompleteTime;

    @ApiModelProperty("完成时间(大)")
    private Integer maxCompleteTime;

    protected WorkrobotSaveOrUpdateDTO(WorkrobotSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.minWorkAmount = b.minWorkAmount;
        this.maxWorkAmount = b.maxWorkAmount;
        this.minWorkTime = b.minWorkTime;
        this.maxWorkTime = b.maxWorkTime;
        this.orderCount = b.orderCount;
        this.minLoanTime = b.minLoanTime;
        this.maxLoanTime = b.maxLoanTime;
        this.minCompleteTime = b.minCompleteTime;
        this.maxCompleteTime = b.maxCompleteTime;
    }

    public WorkrobotSaveOrUpdateDTO(BigDecimal minWorkAmount, BigDecimal maxWorkAmount, String minWorkTime, String maxWorkTime, Integer orderCount, Integer minLoanTime, Integer maxLoanTime, Integer minCompleteTime, Integer maxCompleteTime) {

        this.minWorkAmount = minWorkAmount;
        this.maxWorkAmount = maxWorkAmount;
        this.minWorkTime = minWorkTime;
        this.maxWorkTime = maxWorkTime;
        this.orderCount = orderCount;
        this.minLoanTime = minLoanTime;
        this.maxLoanTime = maxLoanTime;
        this.minCompleteTime = minCompleteTime;
        this.maxCompleteTime = maxCompleteTime;

    }

    public WorkrobotSaveOrUpdateDTO() {
    }

    public static WorkrobotSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new WorkrobotSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkrobotSaveOrUpdateDTO;
    }

    public BigDecimal getMinWorkAmount() {

        return this.minWorkAmount;

    }

    public void setMinWorkAmount(BigDecimal minWorkAmount) {
        this.minWorkAmount = minWorkAmount;
    }

    public BigDecimal getMaxWorkAmount() {

        return this.maxWorkAmount;

    }

    public void setMaxWorkAmount(BigDecimal maxWorkAmount) {
        this.maxWorkAmount = maxWorkAmount;
    }

    public String getMinWorkTime() {

        return this.minWorkTime;

    }

    public void setMinWorkTime(String minWorkTime) {
        this.minWorkTime = minWorkTime;
    }

    public String getMaxWorkTime() {

        return this.maxWorkTime;

    }

    public void setMaxWorkTime(String maxWorkTime) {
        this.maxWorkTime = maxWorkTime;
    }

    public Integer getOrderCount() {

        return this.orderCount;

    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getMinLoanTime() {

        return this.minLoanTime;

    }

    public void setMinLoanTime(Integer minLoanTime) {
        this.minLoanTime = minLoanTime;
    }

    public Integer getMaxLoanTime() {

        return this.maxLoanTime;

    }

    public void setMaxLoanTime(Integer maxLoanTime) {
        this.maxLoanTime = maxLoanTime;
    }

    public Integer getMinCompleteTime() {

        return this.minCompleteTime;

    }

    public void setMinCompleteTime(Integer minCompleteTime) {
        this.minCompleteTime = minCompleteTime;
    }

    public Integer getMaxCompleteTime() {

        return this.maxCompleteTime;

    }

    public void setMaxCompleteTime(Integer maxCompleteTime) {
        this.maxCompleteTime = maxCompleteTime;
    }

    private static final class WorkrobotSaveOrUpdateDTOBuilderImpl extends WorkrobotSaveOrUpdateDTOBuilder<WorkrobotSaveOrUpdateDTO, WorkrobotSaveOrUpdateDTOBuilderImpl> {
        private WorkrobotSaveOrUpdateDTOBuilderImpl() {
        }

        protected WorkrobotSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public WorkrobotSaveOrUpdateDTO build() {
            return new WorkrobotSaveOrUpdateDTO(this);
        }
    }

    public static abstract class WorkrobotSaveOrUpdateDTOBuilder<C extends WorkrobotSaveOrUpdateDTO, B extends WorkrobotSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private BigDecimal minWorkAmount;
        private BigDecimal maxWorkAmount;
        private String minWorkTime;
        private String maxWorkTime;
        private Integer orderCount;
        private Integer minLoanTime;
        private Integer maxLoanTime;
        private Integer minCompleteTime;
        private Integer maxCompleteTime;

        public B minWorkAmount(BigDecimal minWorkAmount) {
            this.minWorkAmount = minWorkAmount;
            return self();
        }

        public B maxWorkAmount(BigDecimal maxWorkAmount) {
            this.maxWorkAmount = maxWorkAmount;
            return self();
        }

        public B minWorkTime(String minWorkTime) {
            this.minWorkTime = minWorkTime;
            return self();
        }

        public B maxWorkTime(String maxWorkTime) {
            this.maxWorkTime = maxWorkTime;
            return self();
        }

        public B orderCount(Integer orderCount) {
            this.orderCount = orderCount;
            return self();
        }

        public B minLoanTime(Integer minLoanTime) {
            this.minLoanTime = minLoanTime;
            return self();
        }

        public B maxLoanTime(Integer maxLoanTime) {
            this.maxLoanTime = maxLoanTime;
            return self();
        }

        public B minCompleteTime(Integer minCompleteTime) {
            this.minCompleteTime = minCompleteTime;
            return self();
        }

        public B maxCompleteTime(Integer maxCompleteTime) {
            this.maxCompleteTime = maxCompleteTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

