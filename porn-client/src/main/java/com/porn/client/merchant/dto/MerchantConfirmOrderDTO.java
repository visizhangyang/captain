package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class MerchantConfirmOrderDTO
        extends BaseDTO {

    @ApiModelProperty("确认金额")
    private BigDecimal amount;

    protected MerchantConfirmOrderDTO(MerchantConfirmOrderDTOBuilder<?, ?> b) {
        super(b);
        this.amount = b.amount;
    }

    public MerchantConfirmOrderDTO(BigDecimal amount) {

        this.amount = amount;

    }

    public MerchantConfirmOrderDTO() {
    }

    public static MerchantConfirmOrderDTOBuilder<?, ?> builder() {
        return new MerchantConfirmOrderDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantConfirmOrderDTO;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    private static final class MerchantConfirmOrderDTOBuilderImpl extends MerchantConfirmOrderDTOBuilder<MerchantConfirmOrderDTO, MerchantConfirmOrderDTOBuilderImpl> {
        private MerchantConfirmOrderDTOBuilderImpl() {
        }

        protected MerchantConfirmOrderDTOBuilderImpl self() {
            return this;
        }

        public MerchantConfirmOrderDTO build() {
            return new MerchantConfirmOrderDTO(this);
        }
    }

    public static abstract class MerchantConfirmOrderDTOBuilder<C extends MerchantConfirmOrderDTO, B extends MerchantConfirmOrderDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private BigDecimal amount;

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

