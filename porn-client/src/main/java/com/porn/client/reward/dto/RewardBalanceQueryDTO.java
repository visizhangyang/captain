package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RewardBalanceQueryDTO
        extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    protected RewardBalanceQueryDTO(RewardBalanceQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
    }

    public RewardBalanceQueryDTO(Long accountId) {

        this.accountId = accountId;

    }

    public RewardBalanceQueryDTO() {
    }

    public static RewardBalanceQueryDTOBuilder<?, ?> builder() {
        return new RewardBalanceQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RewardBalanceQueryDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    private static final class RewardBalanceQueryDTOBuilderImpl extends RewardBalanceQueryDTOBuilder<RewardBalanceQueryDTO, RewardBalanceQueryDTOBuilderImpl> {
        private RewardBalanceQueryDTOBuilderImpl() {
        }

        protected RewardBalanceQueryDTOBuilderImpl self() {
            return this;
        }

        public RewardBalanceQueryDTO build() {
            return new RewardBalanceQueryDTO(this);
        }
    }

    public static abstract class RewardBalanceQueryDTOBuilder<C extends RewardBalanceQueryDTO, B extends RewardBalanceQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

