package com.porn.client.autowork.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoWorkSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
    private Long accountId;
    @ApiModelProperty("最小搬砖次数")
    private Integer minWorkCount;
    @ApiModelProperty("最大搬砖次数")
    private Integer maxWorkCount;
    @ApiModelProperty("最小搬砖金额")
    private BigDecimal minWorkAmount;
    @ApiModelProperty("最大搬砖金额")
    private BigDecimal maxWorkAmount;
    @ApiModelProperty("最小搬砖间隔")
    private Integer minWorkSpace;
    @ApiModelProperty("最大搬砖间隔")
    private Integer maxWorkSpace;
    @ApiModelProperty("搬砖开始时间")
    private LocalDateTime minWorkTime;
    @ApiModelProperty("搬砖结束时间")
    private LocalDateTime maxWorkTime;
    @ApiModelProperty("放款时间(小)")
    private Integer minLoanTime;
    @ApiModelProperty("放款时间(大)")
    private Integer maxLoanTime;
    @ApiModelProperty("完成时间(小)")
    private Integer minCompleteTime;
    @ApiModelProperty("完成时间(大)")
    private Integer maxCompleteTime;

    protected AutoWorkSaveOrUpdateDTO(AutoWorkSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.minWorkCount = b.minWorkCount;
        this.maxWorkCount = b.maxWorkCount;
        this.minWorkAmount = b.minWorkAmount;
        this.maxWorkAmount = b.maxWorkAmount;
        this.minWorkSpace = b.minWorkSpace;
        this.maxWorkSpace = b.maxWorkSpace;
        this.minWorkTime = b.minWorkTime;
        this.maxWorkTime = b.maxWorkTime;
        this.minLoanTime = b.minLoanTime;
        this.maxLoanTime = b.maxLoanTime;
        this.minCompleteTime = b.minCompleteTime;
        this.maxCompleteTime = b.maxCompleteTime;
    }

    public AutoWorkSaveOrUpdateDTO(Long accountId, Integer minWorkCount, Integer maxWorkCount, BigDecimal minWorkAmount, BigDecimal maxWorkAmount, Integer minWorkSpace, Integer maxWorkSpace, LocalDateTime minWorkTime, LocalDateTime maxWorkTime, Integer minLoanTime, Integer maxLoanTime, Integer minCompleteTime, Integer maxCompleteTime) {

        this.accountId = accountId;
        this.minWorkCount = minWorkCount;
        this.maxWorkCount = maxWorkCount;
        this.minWorkAmount = minWorkAmount;
        this.maxWorkAmount = maxWorkAmount;
        this.minWorkSpace = minWorkSpace;
        this.maxWorkSpace = maxWorkSpace;
        this.minWorkTime = minWorkTime;
        this.maxWorkTime = maxWorkTime;
        this.minLoanTime = minLoanTime;
        this.maxLoanTime = maxLoanTime;
        this.minCompleteTime = minCompleteTime;
        this.maxCompleteTime = maxCompleteTime;

    }

    public AutoWorkSaveOrUpdateDTO() {
    }

    public static AutoWorkSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AutoWorkSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AutoWorkSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getMinWorkCount() {

        return this.minWorkCount;

    }

    public void setMinWorkCount(Integer minWorkCount) {
        this.minWorkCount = minWorkCount;
    }

    public Integer getMaxWorkCount() {

        return this.maxWorkCount;

    }

    public void setMaxWorkCount(Integer maxWorkCount) {
        this.maxWorkCount = maxWorkCount;
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

    public Integer getMinWorkSpace() {

        return this.minWorkSpace;

    }

    public void setMinWorkSpace(Integer minWorkSpace) {
        this.minWorkSpace = minWorkSpace;
    }

    public Integer getMaxWorkSpace() {

        return this.maxWorkSpace;

    }

    public void setMaxWorkSpace(Integer maxWorkSpace) {
        this.maxWorkSpace = maxWorkSpace;
    }

    public LocalDateTime getMinWorkTime() {

        return this.minWorkTime;

    }

    public void setMinWorkTime(LocalDateTime minWorkTime) {
        this.minWorkTime = minWorkTime;
    }

    public LocalDateTime getMaxWorkTime() {

        return this.maxWorkTime;

    }

    public void setMaxWorkTime(LocalDateTime maxWorkTime) {
        this.maxWorkTime = maxWorkTime;
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

    private static final class AutoWorkSaveOrUpdateDTOBuilderImpl extends AutoWorkSaveOrUpdateDTOBuilder<AutoWorkSaveOrUpdateDTO, AutoWorkSaveOrUpdateDTOBuilderImpl> {
        private AutoWorkSaveOrUpdateDTOBuilderImpl() {
        }

        protected AutoWorkSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public AutoWorkSaveOrUpdateDTO build() {
            return new AutoWorkSaveOrUpdateDTO(this);
        }
    }

    public static abstract class AutoWorkSaveOrUpdateDTOBuilder<C extends AutoWorkSaveOrUpdateDTO, B extends AutoWorkSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Integer minWorkCount;
        private Integer maxWorkCount;
        private BigDecimal minWorkAmount;
        private BigDecimal maxWorkAmount;
        private Integer minWorkSpace;
        private Integer maxWorkSpace;
        private LocalDateTime minWorkTime;
        private LocalDateTime maxWorkTime;
        private Integer minLoanTime;
        private Integer maxLoanTime;
        private Integer minCompleteTime;
        private Integer maxCompleteTime;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B minWorkCount(Integer minWorkCount) {
            this.minWorkCount = minWorkCount;
            return self();
        }

        public B maxWorkCount(Integer maxWorkCount) {
            this.maxWorkCount = maxWorkCount;
            return self();
        }

        public B minWorkAmount(BigDecimal minWorkAmount) {
            this.minWorkAmount = minWorkAmount;
            return self();
        }

        public B maxWorkAmount(BigDecimal maxWorkAmount) {
            this.maxWorkAmount = maxWorkAmount;
            return self();
        }

        public B minWorkSpace(Integer minWorkSpace) {
            this.minWorkSpace = minWorkSpace;
            return self();
        }

        public B maxWorkSpace(Integer maxWorkSpace) {
            this.maxWorkSpace = maxWorkSpace;
            return self();
        }

        public B minWorkTime(LocalDateTime minWorkTime) {
            this.minWorkTime = minWorkTime;
            return self();
        }

        public B maxWorkTime(LocalDateTime maxWorkTime) {
            this.maxWorkTime = maxWorkTime;
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

