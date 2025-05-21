
package com.porn.client.autowork.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

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

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

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

    public void setMinWorkCount(Integer minWorkCount) {
        this.minWorkCount = minWorkCount;
    }

    public void setMaxWorkCount(Integer maxWorkCount) {
        this.maxWorkCount = maxWorkCount;
    }

    public void setMinWorkAmount(BigDecimal minWorkAmount) {
        this.minWorkAmount = minWorkAmount;
    }

    public void setMaxWorkAmount(BigDecimal maxWorkAmount) {
        this.maxWorkAmount = maxWorkAmount;
    }

    public void setMinWorkSpace(Integer minWorkSpace) {
        this.minWorkSpace = minWorkSpace;
    }

    public void setMaxWorkSpace(Integer maxWorkSpace) {
        this.maxWorkSpace = maxWorkSpace;
    }

    public void setMinWorkTime(LocalDateTime minWorkTime) {
        this.minWorkTime = minWorkTime;
    }

    public void setMaxWorkTime(LocalDateTime maxWorkTime) {
        this.maxWorkTime = maxWorkTime;
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
        return other instanceof AutoWorkSaveOrUpdateDTO;
    }



    /* 18 */
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

    public static AutoWorkSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AutoWorkSaveOrUpdateDTOBuilderImpl();
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

    public AutoWorkSaveOrUpdateDTO(Long accountId, Integer minWorkCount, Integer maxWorkCount, BigDecimal minWorkAmount, BigDecimal maxWorkAmount, Integer minWorkSpace, Integer maxWorkSpace, LocalDateTime minWorkTime, LocalDateTime maxWorkTime, Integer minLoanTime, Integer maxLoanTime, Integer minCompleteTime, Integer maxCompleteTime) {
        /* 19 */
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

    
    
    public Long getAccountId() {
        /* 24 */
        return this.accountId;
        
    }

    
    public Integer getMinWorkCount() {
        /* 27 */
        return this.minWorkCount;
        
    }

    
    public Integer getMaxWorkCount() {
        /* 30 */
        return this.maxWorkCount;
        
    }

    
    public BigDecimal getMinWorkAmount() {
        /* 33 */
        return this.minWorkAmount;
        
    }

    
    public BigDecimal getMaxWorkAmount() {
        /* 36 */
        return this.maxWorkAmount;
        
    }

    
    public Integer getMinWorkSpace() {
        /* 39 */
        return this.minWorkSpace;
        
    }

    
    public Integer getMaxWorkSpace() {
        /* 42 */
        return this.maxWorkSpace;
        
    }

    
    public LocalDateTime getMinWorkTime() {
        /* 45 */
        return this.minWorkTime;
        
    }

    
    public LocalDateTime getMaxWorkTime() {
        /* 48 */
        return this.maxWorkTime;
        
    }

    
    public Integer getMinLoanTime() {
        /* 51 */
        return this.minLoanTime;
        
    }

    
    public Integer getMaxLoanTime() {
        /* 54 */
        return this.maxLoanTime;
        
    }

    
    public Integer getMinCompleteTime() {
        /* 57 */
        return this.minCompleteTime;
        
    }

    
    public Integer getMaxCompleteTime() {
        /* 60 */
        return this.maxCompleteTime;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/autowork/dto/AutoWorkSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */