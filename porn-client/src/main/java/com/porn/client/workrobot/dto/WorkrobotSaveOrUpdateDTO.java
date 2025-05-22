
package com.porn.client.workrobot.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;


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


    /* 17 */
    public void setMinWorkAmount(BigDecimal minWorkAmount) {
        this.minWorkAmount = minWorkAmount;
    }

    public void setMaxWorkAmount(BigDecimal maxWorkAmount) {
        this.maxWorkAmount = maxWorkAmount;
    }

    public void setMinWorkTime(String minWorkTime) {
        this.minWorkTime = minWorkTime;
    }

    public void setMaxWorkTime(String maxWorkTime) {
        this.maxWorkTime = maxWorkTime;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public void setMinLoanTime(Integer minLoanTime) {
        this.minLoanTime = minLoanTime;
    }

    public void setMaxLoanTime(Integer maxLoanTime) {
        this.maxLoanTime = maxLoanTime;
    }

    public void setMinCompleteTime(Integer minCompleteTime) {
        this.minCompleteTime = minCompleteTime;
    }

    public void setMaxCompleteTime(Integer maxCompleteTime) {
        this.maxCompleteTime = maxCompleteTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof WorkrobotSaveOrUpdateDTO;
    }



    /* 18 */
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

    public static WorkrobotSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new WorkrobotSaveOrUpdateDTOBuilderImpl();
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

        public B minWorkAmount(BigDecimal minWorkAmount) {
            this.minWorkAmount = minWorkAmount;
            return self();
        }

        private Integer orderCount;
        private Integer minLoanTime;
        private Integer maxLoanTime;
        private Integer minCompleteTime;
        private Integer maxCompleteTime;

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

    public WorkrobotSaveOrUpdateDTO(BigDecimal minWorkAmount, BigDecimal maxWorkAmount, String minWorkTime, String maxWorkTime, Integer orderCount, Integer minLoanTime, Integer maxLoanTime, Integer minCompleteTime, Integer maxCompleteTime) {
        /* 19 */
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



    public BigDecimal getMinWorkAmount() {
        /* 24 */
        return this.minWorkAmount;

    }


    public BigDecimal getMaxWorkAmount() {
        /* 27 */
        return this.maxWorkAmount;

    }


    public String getMinWorkTime() {
        /* 30 */
        return this.minWorkTime;

    }


    public String getMaxWorkTime() {
        /* 33 */
        return this.maxWorkTime;

    }


    public Integer getOrderCount() {
        /* 36 */
        return this.orderCount;

    }


    public Integer getMinLoanTime() {
        /* 39 */
        return this.minLoanTime;

    }


    public Integer getMaxLoanTime() {
        /* 42 */
        return this.maxLoanTime;

    }


    public Integer getMinCompleteTime() {
        /* 45 */
        return this.minCompleteTime;

    }


    public Integer getMaxCompleteTime() {
        /* 48 */
        return this.maxCompleteTime;

    }
}


