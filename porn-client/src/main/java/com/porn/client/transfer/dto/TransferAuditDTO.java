
package com.porn.client.transfer.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;







 public class TransferAuditDTO
         extends BaseDTO
         {


    protected boolean canEqual(Object other) {
        return other instanceof TransferAuditDTO;
    }



    /* 14 */
    protected TransferAuditDTO(TransferAuditDTOBuilder<?, ?> b) {
        super(b);
    }

    public static TransferAuditDTOBuilder<?, ?> builder() {
        return new TransferAuditDTOBuilderImpl();
    }

    private static final class TransferAuditDTOBuilderImpl extends TransferAuditDTOBuilder<TransferAuditDTO, TransferAuditDTOBuilderImpl> {
        protected TransferAuditDTOBuilderImpl self() {
            return this;
        }

        private TransferAuditDTOBuilderImpl() {
        }

        public TransferAuditDTO build() {
            return new TransferAuditDTO(this);
        }
    }

    public static abstract class TransferAuditDTOBuilder<C extends TransferAuditDTO, B extends TransferAuditDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {




        protected abstract B self();



        public abstract C build();
    }




    public TransferAuditDTO() {
    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/transfer/dto/TransferAuditDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */