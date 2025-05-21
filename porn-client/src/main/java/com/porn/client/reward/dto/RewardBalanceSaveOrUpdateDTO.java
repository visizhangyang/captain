
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;







 public class RewardBalanceSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("操作次数")
     private BigDecimal availableCount;

    @ApiModelProperty("类型, RewardRecordTypeEnum")
     private Integer type;


    /* 17 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAvailableCount(BigDecimal availableCount) {
        this.availableCount = availableCount;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RewardBalanceSaveOrUpdateDTO;
    }



    /* 18 */
    protected RewardBalanceSaveOrUpdateDTO(RewardBalanceSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.availableCount = b.availableCount;
        this.type = b.type;
    }

    public static RewardBalanceSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RewardBalanceSaveOrUpdateDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private BigDecimal availableCount;
        private Integer type;

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

    public RewardBalanceSaveOrUpdateDTO(Long accountId, BigDecimal availableCount, Integer type) {
        /* 19 */
        this.accountId = accountId;
        this.availableCount = availableCount;
        this.type = type;

    }


    public RewardBalanceSaveOrUpdateDTO() {
    }



    public Long getAccountId() {
        /* 24 */
        return this.accountId;

    }


    public BigDecimal getAvailableCount() {
        /* 27 */
        return this.availableCount;

    }


    public Integer getType() {
        /* 30 */
        return this.type;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/dto/RewardBalanceSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */