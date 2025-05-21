
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;

import java.math.BigDecimal;


 public class OperateRewardBalanceDTO extends BaseDTO {
    
    @ApiModelProperty("账户ID")
     private Long accountId;
    
    @ApiModelProperty("操作后次数")
     private BigDecimal operateCount;
    
    @ApiModelProperty("类型, RewardRecordTypeEnum")
     private Integer type;
    
    @ApiModelProperty("业务类型, RuleTypeEnum")
     private Integer bizType;
    
    @ApiModelProperty("业务ID")
     private String bizId;

    
    /* 17 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setOperateCount(BigDecimal operateCount) {
        this.operateCount = operateCount;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof OperateRewardBalanceDTO;
    }



    /* 18 */
    protected OperateRewardBalanceDTO(OperateRewardBalanceDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.operateCount = b.operateCount;
        this.type = b.type;
        this.bizType = b.bizType;
        this.bizId = b.bizId;
    }

    public static OperateRewardBalanceDTOBuilder<?, ?> builder() {
        return new OperateRewardBalanceDTOBuilderImpl();
    }

    private static final class OperateRewardBalanceDTOBuilderImpl extends OperateRewardBalanceDTOBuilder<OperateRewardBalanceDTO, OperateRewardBalanceDTOBuilderImpl> {
        private OperateRewardBalanceDTOBuilderImpl() {
        }

        protected OperateRewardBalanceDTOBuilderImpl self() {
            return this;
        }

        public OperateRewardBalanceDTO build() {
            return new OperateRewardBalanceDTO(this);
        }
    }

    public static abstract class OperateRewardBalanceDTOBuilder<C extends OperateRewardBalanceDTO, B extends OperateRewardBalanceDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private BigDecimal operateCount;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Integer type;
        private Integer bizType;
        private String bizId;

        public B operateCount(BigDecimal operateCount) {
            this.operateCount = operateCount;
            return self();
        }

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B bizType(Integer bizType) {
            this.bizType = bizType;
            return self();
        }

        public B bizId(String bizId) {
            this.bizId = bizId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public OperateRewardBalanceDTO(Long accountId, BigDecimal operateCount, Integer type, Integer bizType, String bizId) {
        /* 19 */
        this.accountId = accountId;
        this.operateCount = operateCount;
        this.type = type;
        this.bizType = bizType;
        this.bizId = bizId;
        
    }

    
    public OperateRewardBalanceDTO() {
    }

    
    
    public Long getAccountId() {
        /* 24 */
        return this.accountId;
        
    }

    
    public BigDecimal getOperateCount() {
        /* 27 */
        return this.operateCount;
        
    }

    
    public Integer getType() {
        /* 30 */
        return this.type;
        
    }

    
    public Integer getBizType() {
        /* 33 */
        return this.bizType;
        
    }

    
    public String getBizId() {
        /* 36 */
        return this.bizId;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/dto/OperateRewardBalanceDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */