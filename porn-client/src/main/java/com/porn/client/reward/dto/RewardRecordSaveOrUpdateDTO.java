
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.math.BigDecimal;

 public class RewardRecordSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("操作前可用次数")
     private BigDecimal beforeAvailableCount;

    @ApiModelProperty("操作后可用次数")
     private BigDecimal afterAvailableCount;

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

    public void setBeforeAvailableCount(BigDecimal beforeAvailableCount) {
        this.beforeAvailableCount = beforeAvailableCount;
    }

    public void setAfterAvailableCount(BigDecimal afterAvailableCount) {
        this.afterAvailableCount = afterAvailableCount;
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
        return other instanceof RewardRecordSaveOrUpdateDTO;
    }



    /* 18 */
    protected RewardRecordSaveOrUpdateDTO(RewardRecordSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.beforeAvailableCount = b.beforeAvailableCount;
        this.afterAvailableCount = b.afterAvailableCount;
        this.operateCount = b.operateCount;
        this.type = b.type;
        this.bizType = b.bizType;
        this.bizId = b.bizId;
    }

    public static RewardRecordSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RewardRecordSaveOrUpdateDTOBuilderImpl();
    }

    private static final class RewardRecordSaveOrUpdateDTOBuilderImpl extends RewardRecordSaveOrUpdateDTOBuilder<RewardRecordSaveOrUpdateDTO, RewardRecordSaveOrUpdateDTOBuilderImpl> {
        private RewardRecordSaveOrUpdateDTOBuilderImpl() {
        }

        protected RewardRecordSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RewardRecordSaveOrUpdateDTO build() {
            return new RewardRecordSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RewardRecordSaveOrUpdateDTOBuilder<C extends RewardRecordSaveOrUpdateDTO, B extends RewardRecordSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private BigDecimal beforeAvailableCount;
        private BigDecimal afterAvailableCount;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private BigDecimal operateCount;
        private Integer type;
        private Integer bizType;
        private String bizId;

        public B beforeAvailableCount(BigDecimal beforeAvailableCount) {
            this.beforeAvailableCount = beforeAvailableCount;
            return self();
        }

        public B afterAvailableCount(BigDecimal afterAvailableCount) {
            this.afterAvailableCount = afterAvailableCount;
            return self();
        }

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

    public RewardRecordSaveOrUpdateDTO(Long accountId, BigDecimal beforeAvailableCount, BigDecimal afterAvailableCount, BigDecimal operateCount, Integer type, Integer bizType, String bizId) {
        /* 19 */
        this.accountId = accountId;
        this.beforeAvailableCount = beforeAvailableCount;
        this.afterAvailableCount = afterAvailableCount;
        this.operateCount = operateCount;
        this.type = type;
        this.bizType = bizType;
        this.bizId = bizId;

    }


    public RewardRecordSaveOrUpdateDTO() {
    }



    public Long getAccountId() {
        /* 24 */
        return this.accountId;

    }


    public BigDecimal getBeforeAvailableCount() {
        /* 27 */
        return this.beforeAvailableCount;

    }


    public BigDecimal getAfterAvailableCount() {
        /* 30 */
        return this.afterAvailableCount;

    }


    public BigDecimal getOperateCount() {
        /* 33 */
        return this.operateCount;

    }


    public Integer getType() {
        /* 36 */
        return this.type;

    }


    public Integer getBizType() {
        /* 39 */
        return this.bizType;

    }


    public String getBizId() {
        /* 42 */
        return this.bizId;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/dto/RewardRecordSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */