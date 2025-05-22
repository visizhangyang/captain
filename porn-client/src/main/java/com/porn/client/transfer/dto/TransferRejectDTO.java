
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


