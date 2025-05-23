package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;

public class MerchantDeleteDTO
        extends BaseDTO {

    protected MerchantDeleteDTO(MerchantDeleteDTOBuilder<?, ?> b) {
        super(b);
    }

    public MerchantDeleteDTO() {
    }

    public static MerchantDeleteDTOBuilder<?, ?> builder() {
        return new MerchantDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantDeleteDTO;
    }

    private static final class MerchantDeleteDTOBuilderImpl extends MerchantDeleteDTOBuilder<MerchantDeleteDTO, MerchantDeleteDTOBuilderImpl> {
        private MerchantDeleteDTOBuilderImpl() {
        }

        protected MerchantDeleteDTOBuilderImpl self() {
            return this;
        }

        public MerchantDeleteDTO build() {
            return new MerchantDeleteDTO(this);
        }
    }

    public static abstract class MerchantDeleteDTOBuilder<C extends MerchantDeleteDTO, B extends MerchantDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {

        protected abstract B self();

        public abstract C build();
    }

}

