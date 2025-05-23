package com.porn.client.mobile.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MerchantTradeRequestDTO
        extends BasePageDTO
        implements Serializable {

    @ApiModelProperty("商户id")
    private Long merchantId;

    protected MerchantTradeRequestDTO(MerchantTradeRequestDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
    }

    public MerchantTradeRequestDTO(Long merchantId) {

        this.merchantId = merchantId;

    }

    public MerchantTradeRequestDTO() {
    }

    public static MerchantTradeRequestDTOBuilder<?, ?> builder() {
        return new MerchantTradeRequestDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantTradeRequestDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    private static final class MerchantTradeRequestDTOBuilderImpl extends MerchantTradeRequestDTOBuilder<MerchantTradeRequestDTO, MerchantTradeRequestDTOBuilderImpl> {
        private MerchantTradeRequestDTOBuilderImpl() {
        }

        protected MerchantTradeRequestDTOBuilderImpl self() {
            return this;
        }

        public MerchantTradeRequestDTO build() {
            return new MerchantTradeRequestDTO(this);
        }
    }

    public static abstract class MerchantTradeRequestDTOBuilder<C extends MerchantTradeRequestDTO, B extends MerchantTradeRequestDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long merchantId;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

