package com.porn.client.transfer.dto;

import com.porn.client.common.dto.BaseDTO;

public class TransferDeleteDTO
        extends BaseDTO {

    protected TransferDeleteDTO(TransferDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public TransferDeleteDTO() {
    }

    public static TransferDeleteDTOBuilder<?, ?> builder() {
        return new TransferDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TransferDeleteDTO;
    }

    private static final class TransferDeleteDTOBuilderImpl extends TransferDeleteDTOBuilder<TransferDeleteDTO, TransferDeleteDTOBuilderImpl> {
        private TransferDeleteDTOBuilderImpl() {
        }

        protected TransferDeleteDTOBuilderImpl self() {
            return this;
        }

        public TransferDeleteDTO build() {
            return new TransferDeleteDTO(this);
        }
    }

    public static abstract class TransferDeleteDTOBuilder<C extends TransferDeleteDTO, B extends TransferDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

