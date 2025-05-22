
package com.porn.client.transfer.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class TransferDeleteDTO
         extends BaseDTO
         {
    

    protected boolean canEqual(Object other) {
        return other instanceof TransferDeleteDTO;
    }



    /* 14 */
    protected TransferDeleteDTO(TransferDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public static TransferDeleteDTOBuilder<?, ?> builder() {
        return new TransferDeleteDTOBuilderImpl();
    }

    private static final class TransferDeleteDTOBuilderImpl extends TransferDeleteDTOBuilder<TransferDeleteDTO, TransferDeleteDTOBuilderImpl> {
        protected TransferDeleteDTOBuilderImpl self() {
            return this;
        }

        private TransferDeleteDTOBuilderImpl() {
        }

        public TransferDeleteDTO build() {
            return new TransferDeleteDTO(this);
        }
    }

    public static abstract class TransferDeleteDTOBuilder<C extends TransferDeleteDTO, B extends TransferDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        
        
        
        protected abstract B self();

        
        
        public abstract C build();
    }

    
    
    
    public TransferDeleteDTO() {
    }
    
}


