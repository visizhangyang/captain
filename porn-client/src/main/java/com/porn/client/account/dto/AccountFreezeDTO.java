
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;







 public class AccountFreezeDTO extends BaseDTO {

    @ApiModelProperty("0-冻结, 1-解冻")
     private Integer freezeType;

    @ApiModelProperty("金额")
     private BigDecimal amount;



    public void setFreezeType(Integer freezeType) {
        /* 16 */
        this.freezeType = freezeType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountFreezeDTO;
    }



    /* 17 */
    protected AccountFreezeDTO(AccountFreezeDTOBuilder<?, ?> b) {
        super(b);
        this.freezeType = b.freezeType;
        this.amount = b.amount;
    }

    public static AccountFreezeDTOBuilder<?, ?> builder() {
        return new AccountFreezeDTOBuilderImpl();
    }

    private static final class AccountFreezeDTOBuilderImpl extends AccountFreezeDTOBuilder<AccountFreezeDTO, AccountFreezeDTOBuilderImpl> {
        private AccountFreezeDTOBuilderImpl() {
        }

        protected AccountFreezeDTOBuilderImpl self() {
            return this;
        }

        public AccountFreezeDTO build() {
            return new AccountFreezeDTO(this);
        }
    }

    public static abstract class AccountFreezeDTOBuilder<C extends AccountFreezeDTO, B extends AccountFreezeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer freezeType;

        public B freezeType(Integer freezeType) {
            this.freezeType = freezeType;
            return self();
        }

        private BigDecimal amount;

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        protected abstract B self();

        public abstract C build();


    }

    public AccountFreezeDTO() {
    }

    public AccountFreezeDTO(Integer freezeType, BigDecimal amount) {
        /* 19 */
        this.freezeType = freezeType;
        this.amount = amount;

    }



    public Integer getFreezeType() {
        /* 23 */
        return this.freezeType;

    }


    public BigDecimal getAmount() {
        /* 26 */
        return this.amount;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountFreezeDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */