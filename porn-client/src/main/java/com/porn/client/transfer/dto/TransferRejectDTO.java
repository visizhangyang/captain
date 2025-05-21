
package com.porn.client.transfer.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class TransferRejectDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof TransferRejectDTO;
    }



    /* 14 */
    protected TransferRejectDTO(TransferRejectDTOBuilder<?, ?> b) {
        super(b);
    }

    public static TransferRejectDTOBuilder<?, ?> builder() {
        return new TransferRejectDTOBuilderImpl();
    }

    private static final class TransferRejectDTOBuilderImpl extends TransferRejectDTOBuilder<TransferRejectDTO, TransferRejectDTOBuilderImpl> {
        protected TransferRejectDTOBuilderImpl self() {
            return this;
        }

        private TransferRejectDTOBuilderImpl() {
        }

        public TransferRejectDTO build() {
            return new TransferRejectDTO(this);
        }
    }

    public static abstract class TransferRejectDTOBuilder<C extends TransferRejectDTO, B extends TransferRejectDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public TransferRejectDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/transfer/dto/TransferRejectDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */