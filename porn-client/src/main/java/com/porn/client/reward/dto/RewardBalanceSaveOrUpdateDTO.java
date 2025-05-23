package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RewardBalanceSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("操作次数")
    private BigDecimal availableCount;

    @ApiModelProperty("类型, RewardRecordTypeEnum")
    private Integer type;

    protected RewardBalanceSaveOrUpdateDTO(RewardBalanceSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.availableCount = b.availableCount;
        this.type = b.type;
    }

    public RewardBalanceSaveOrUpdateDTO(Long accountId, BigDecimal availableCount, Integer type) {

        this.accountId = accountId;
        this.availableCount = availableCount;
        this.type = type;

    }

    public RewardBalanceSaveOrUpdateDTO() {
    }

    public static RewardBalanceSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RewardBalanceSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RewardBalanceSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAvailableCount() {

        return this.availableCount;

    }

    public void setAvailableCount(BigDecimal availableCount) {
        this.availableCount = availableCount;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    private static final class RewardBalanceSaveOrUpdateDTOBuilderImpl extends RewardBalanceSaveOrUpdateDTOBuilder<RewardBalanceSaveOrUpdateDTO, RewardBalanceSaveOrUpdateDTOBuilderImpl> {
        private RewardBalanceSaveOrUpdateDTOBuilderImpl() {
        }

        protected RewardBalanceSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RewardBalanceSaveOrUpdateDTO build() {
            return new RewardBalanceSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RewardBalanceSaveOrUpdateDTOBuilder<C extends RewardBalanceSaveOrUpdateDTO, B extends RewardBalanceSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private BigDecimal availableCount;
        private Integer type;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B availableCount(BigDecimal availableCount) {
            this.availableCount = availableCount;
            return self();
        }

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

