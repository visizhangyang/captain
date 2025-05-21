
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;







 public class MerchantConfirmOrderDTO
         extends BaseDTO
         {

    @ApiModelProperty("确认金额")
     private BigDecimal amount;



    public void setAmount(BigDecimal amount) {
        /* 16 */
        this.amount = amount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantConfirmOrderDTO;
    }



    /* 17 */
    protected MerchantConfirmOrderDTO(MerchantConfirmOrderDTOBuilder<?, ?> b) {
        super(b);
        this.amount = b.amount;
    }

    public static MerchantConfirmOrderDTOBuilder<?, ?> builder() {
        return new MerchantConfirmOrderDTOBuilderImpl();
    }

    private static final class MerchantConfirmOrderDTOBuilderImpl extends MerchantConfirmOrderDTOBuilder<MerchantConfirmOrderDTO, MerchantConfirmOrderDTOBuilderImpl> {
        private MerchantConfirmOrderDTOBuilderImpl() {
        }

        protected MerchantConfirmOrderDTOBuilderImpl self() {
            return this;
        }

        public MerchantConfirmOrderDTO build() {
            return new MerchantConfirmOrderDTO(this);
        }
    }

    public static abstract class MerchantConfirmOrderDTOBuilder<C extends MerchantConfirmOrderDTO, B extends MerchantConfirmOrderDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        private BigDecimal amount;

        protected abstract B self();

        public abstract C build();

    }

    public MerchantConfirmOrderDTO(BigDecimal amount) {
        /* 18 */
        this.amount = amount;

    }


    public MerchantConfirmOrderDTO() {
    }



    public BigDecimal getAmount() {
        /* 23 */
        return this.amount;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/dto/MerchantConfirmOrderDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */