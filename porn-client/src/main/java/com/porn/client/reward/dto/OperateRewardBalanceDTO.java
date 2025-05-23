package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected OperateRewardBalanceDTO(OperateRewardBalanceDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.operateCount = b.operateCount;
        this.type = b.type;
        this.bizType = b.bizType;
        this.bizId = b.bizId;
    }

    public OperateRewardBalanceDTO(Long accountId, BigDecimal operateCount, Integer type, Integer bizType, String bizId) {

        this.accountId = accountId;
        this.operateCount = operateCount;
        this.type = type;
        this.bizType = bizType;
        this.bizId = bizId;

    }

    public OperateRewardBalanceDTO() {
    }

    public static OperateRewardBalanceDTOBuilder<?, ?> builder() {
        return new OperateRewardBalanceDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof OperateRewardBalanceDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
        private Integer type;
        private Integer bizType;
        private String bizId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
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

