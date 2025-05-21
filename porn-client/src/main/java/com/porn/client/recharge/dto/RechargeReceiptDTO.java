
package com.porn.client.recharge.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class RechargeReceiptDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof RechargeReceiptDTO;
    }



    /* 14 */
    protected RechargeReceiptDTO(RechargeReceiptDTOBuilder<?, ?> b) {
        super(b);
    }

    public static RechargeReceiptDTOBuilder<?, ?> builder() {
        return new RechargeReceiptDTOBuilderImpl();
    }

    private static final class RechargeReceiptDTOBuilderImpl extends RechargeReceiptDTOBuilder<RechargeReceiptDTO, RechargeReceiptDTOBuilderImpl> {
        protected RechargeReceiptDTOBuilderImpl self() {
            return this;
        }

        private RechargeReceiptDTOBuilderImpl() {
        }

        public RechargeReceiptDTO build() {
            return new RechargeReceiptDTO(this);
        }
    }

    public static abstract class RechargeReceiptDTOBuilder<C extends RechargeReceiptDTO, B extends RechargeReceiptDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public RechargeReceiptDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/dto/RechargeReceiptDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */