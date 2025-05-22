
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;







 public class AccountAddOrSubDTO extends BaseDTO {

    @ApiModelProperty("0-加, 1-减")
     private Integer type;

    @ApiModelProperty("金额")
     private BigDecimal amount;



    public void setType(Integer type) {
        /* 16 */
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountAddOrSubDTO;
    }



    /* 17 */
    protected AccountAddOrSubDTO(AccountAddOrSubDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.amount = b.amount;
    }

    public static AccountAddOrSubDTOBuilder<?, ?> builder() {
        return new AccountAddOrSubDTOBuilderImpl();
    }

    private static final class AccountAddOrSubDTOBuilderImpl extends AccountAddOrSubDTOBuilder<AccountAddOrSubDTO, AccountAddOrSubDTOBuilderImpl> {
        private AccountAddOrSubDTOBuilderImpl() {
        }

        protected AccountAddOrSubDTOBuilderImpl self() {
            return this;
        }

        public AccountAddOrSubDTO build() {
            return new AccountAddOrSubDTO(this);
        }
    }

    public static abstract class AccountAddOrSubDTOBuilder<C extends AccountAddOrSubDTO, B extends AccountAddOrSubDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;

        public B type(Integer type) {
            this.type = type;
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

    public AccountAddOrSubDTO() {
    }

    public AccountAddOrSubDTO(Integer type, BigDecimal amount) {
        /* 19 */
        this.type = type;
        this.amount = amount;

    }



    public Integer getType() {
        /* 23 */
        return this.type;

    }


    public BigDecimal getAmount() {
        /* 26 */
        return this.amount;

    }

}


