
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RewardBalanceQueryDTO
         extends BaseDTO
         {

    @ApiModelProperty("账户ID")
     private Long accountId;



    public void setAccountId(Long accountId) {
        /* 15 */
        this.accountId = accountId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RewardBalanceQueryDTO;
    }



    /* 16 */
    protected RewardBalanceQueryDTO(RewardBalanceQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
    }

    public static RewardBalanceQueryDTOBuilder<?, ?> builder() {
        return new RewardBalanceQueryDTOBuilderImpl();
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
        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Long accountId;

        protected abstract B self();

        public abstract C build();

    }

    public RewardBalanceQueryDTO(Long accountId) {
        /* 17 */
        this.accountId = accountId;

    }


    public RewardBalanceQueryDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }

}


