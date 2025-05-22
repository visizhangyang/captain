
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


