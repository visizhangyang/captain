
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;
import com.porn.client.stream.enums.StreamTypeEnum;

import java.math.BigDecimal;





 public class AccountAmountOperateDTO extends BaseDTO {
    
    @ApiModelProperty("金额类型")
     private Integer amountType;
    
    @ApiModelProperty("操作金额")
     private BigDecimal operateAmount;
    
    @ApiModelProperty("业务Id")
     private Long bizId;
    
    @ApiModelProperty("提现到账")
     private StreamTypeEnum streamTypeEnum;

    
    /* 17 */
    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public void setOperateAmount(BigDecimal operateAmount) {
        this.operateAmount = operateAmount;
    }

    public void setBizId(Long bizId) {
        this.bizId = bizId;
    }

    public void setStreamTypeEnum(StreamTypeEnum streamTypeEnum) {
        this.streamTypeEnum = streamTypeEnum;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountAmountOperateDTO;
    }



    /* 18 */
    protected AccountAmountOperateDTO(AccountAmountOperateDTOBuilder<?, ?> b) {
        super(b);
        this.amountType = b.amountType;
        this.operateAmount = b.operateAmount;
        this.bizId = b.bizId;
        this.streamTypeEnum = b.streamTypeEnum;
    }

    public static AccountAmountOperateDTOBuilder<?, ?> builder() {
        return new AccountAmountOperateDTOBuilderImpl();
    }

    private static final class AccountAmountOperateDTOBuilderImpl extends AccountAmountOperateDTOBuilder<AccountAmountOperateDTO, AccountAmountOperateDTOBuilderImpl> {
        private AccountAmountOperateDTOBuilderImpl() {
        }

        protected AccountAmountOperateDTOBuilderImpl self() {
            return this;
        }

        public AccountAmountOperateDTO build() {
            return new AccountAmountOperateDTO(this);
        }
    }

    public static abstract class AccountAmountOperateDTOBuilder<C extends AccountAmountOperateDTO, B extends AccountAmountOperateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer amountType;
        private BigDecimal operateAmount;

        public B amountType(Integer amountType) {
            this.amountType = amountType;
            return self();
        }

        private Long bizId;
        private StreamTypeEnum streamTypeEnum;

        public B operateAmount(BigDecimal operateAmount) {
            this.operateAmount = operateAmount;
            return self();
        }

        public B bizId(Long bizId) {
            this.bizId = bizId;
            return self();
        }

        public B streamTypeEnum(StreamTypeEnum streamTypeEnum) {
            this.streamTypeEnum = streamTypeEnum;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountAmountOperateDTO(Integer amountType, BigDecimal operateAmount, Long bizId, StreamTypeEnum streamTypeEnum) {
        /* 19 */
        this.amountType = amountType;
        this.operateAmount = operateAmount;
        this.bizId = bizId;
        this.streamTypeEnum = streamTypeEnum;
        
    }

    
    public AccountAmountOperateDTO() {
    }

    
    
    public Integer getAmountType() {
        /* 24 */
        return this.amountType;
        
    }

    
    public BigDecimal getOperateAmount() {
        /* 27 */
        return this.operateAmount;
        
    }

    
    public Long getBizId() {
        /* 30 */
        return this.bizId;
        
    }

    
    public StreamTypeEnum getStreamTypeEnum() {
        /* 33 */
        return this.streamTypeEnum;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountAmountOperateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */