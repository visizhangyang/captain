
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountOrderDTO
         extends BaseDTO
         {

    @ApiModelProperty("订单ID")
     private Long orderId;



    public void setOrderId(Long orderId) {
        /* 15 */
        this.orderId = orderId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountOrderDTO;
    }



    /* 16 */
    protected AccountOrderDTO(AccountOrderDTOBuilder<?, ?> b) {
        super(b);
        this.orderId = b.orderId;
    }

    public static AccountOrderDTOBuilder<?, ?> builder() {
        return new AccountOrderDTOBuilderImpl();
    }

    private static final class AccountOrderDTOBuilderImpl extends AccountOrderDTOBuilder<AccountOrderDTO, AccountOrderDTOBuilderImpl> {
        private AccountOrderDTOBuilderImpl() {
        }

        protected AccountOrderDTOBuilderImpl self() {
            return this;
        }

        public AccountOrderDTO build() {
            return new AccountOrderDTO(this);
        }
    }

    public static abstract class AccountOrderDTOBuilder<C extends AccountOrderDTO, B extends AccountOrderDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B orderId(Long orderId) {
            this.orderId = orderId;
            return self();
        }

        private Long orderId;

        protected abstract B self();

        public abstract C build();

    }

    public AccountOrderDTO(Long orderId) {
        /* 17 */
        this.orderId = orderId;

    }


    public AccountOrderDTO() {
    }



    public Long getOrderId() {
        /* 22 */
        return this.orderId;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountOrderDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */