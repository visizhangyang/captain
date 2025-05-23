package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    public RewardRecordSaveOrUpdateDTO(Long accountId, BigDecimal beforeAvailableCount, BigDecimal afterAvailableCount, BigDecimal operateCount, Integer type, Integer bizType, String bizId) {

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

    public static RewardRecordSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RewardRecordSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RewardRecordSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBeforeAvailableCount() {

        return this.beforeAvailableCount;

    }

    public void setBeforeAvailableCount(BigDecimal beforeAvailableCount) {
        this.beforeAvailableCount = beforeAvailableCount;
    }

    public BigDecimal getAfterAvailableCount() {

        return this.afterAvailableCount;

    }

    public void setAfterAvailableCount(BigDecimal afterAvailableCount) {
        this.afterAvailableCount = afterAvailableCount;
    }

    public BigDecimal getOperateCount() {

        return this.operateCount;

    }

    public void setOperateCount(BigDecimal operateCount) {
        this.operateCount = operateCount;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBizType() {

        return this.bizType;

    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getBizId() {

        return this.bizId;

    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
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
        private BigDecimal operateCount;
        private Integer type;
        private Integer bizType;
        private String bizId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

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
}

