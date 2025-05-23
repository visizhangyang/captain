package com.porn.client.transfer.dto;

import com.porn.client.common.dto.BaseDTO;

public class TransferRejectDTO
        extends BaseDTO {

    protected TransferRejectDTO(TransferRejectDTOBuilder<?, ?> b) {
        super(b);
    }

    public TransferRejectDTO() {
    }

    public static TransferRejectDTOBuilder<?, ?> builder() {
        return new TransferRejectDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransferRejectDTO;
    }

    private static final class TransferRejectDTOBuilderImpl extends TransferRejectDTOBuilder<TransferRejectDTO, TransferRejectDTOBuilderImpl> {
        private TransferRejectDTOBuilderImpl() {
        }

        protected TransferRejectDTOBuilderImpl self() {
            return this;
        }

        public TransferRejectDTO build() {
            return new TransferRejectDTO(this);
        }
    }

    public static abstract class TransferRejectDTOBuilder<C extends TransferRejectDTO, B extends TransferRejectDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

