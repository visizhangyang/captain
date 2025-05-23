package com.porn.client.transfer.dto;

import com.porn.client.common.dto.BaseDTO;

public class TransferAuditDTO
        extends BaseDTO {

    protected TransferAuditDTO(TransferAuditDTOBuilder<?, ?> b) {
        super(b);
    }

    public TransferAuditDTO() {
    }

    public static TransferAuditDTOBuilder<?, ?> builder() {
        return new TransferAuditDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransferAuditDTO;
    }

    private static final class TransferAuditDTOBuilderImpl extends TransferAuditDTOBuilder<TransferAuditDTO, TransferAuditDTOBuilderImpl> {
        private TransferAuditDTOBuilderImpl() {
        }

        protected TransferAuditDTOBuilderImpl self() {
            return this;
        }

        public TransferAuditDTO build() {
            return new TransferAuditDTO(this);
        }
    }

    public static abstract class TransferAuditDTOBuilder<C extends TransferAuditDTO, B extends TransferAuditDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

