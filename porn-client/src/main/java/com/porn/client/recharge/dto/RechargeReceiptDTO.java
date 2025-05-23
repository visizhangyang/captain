package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BaseDTO;

public class RechargeReceiptDTO
        extends BaseDTO {

    protected RechargeReceiptDTO(RechargeReceiptDTOBuilder<?, ?> b) {
        super(b);
    }

    public RechargeReceiptDTO() {
    }

    public static RechargeReceiptDTOBuilder<?, ?> builder() {
        return new RechargeReceiptDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeReceiptDTO;
    }

    private static final class RechargeReceiptDTOBuilderImpl extends RechargeReceiptDTOBuilder<RechargeReceiptDTO, RechargeReceiptDTOBuilderImpl> {
        private RechargeReceiptDTOBuilderImpl() {
        }

        protected RechargeReceiptDTOBuilderImpl self() {
            return this;
        }

        public RechargeReceiptDTO build() {
            return new RechargeReceiptDTO(this);
        }
    }

    public static abstract class RechargeReceiptDTOBuilder<C extends RechargeReceiptDTO, B extends RechargeReceiptDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

