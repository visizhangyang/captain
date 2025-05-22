
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;


import java.io.Serializable;







 public class MerchantTradeRequestDTO
         extends BasePageDTO
         implements Serializable {

    @ApiModelProperty("商户id")
     private Long merchantId;



    public void setMerchantId(Long merchantId) {
        /* 16 */
        this.merchantId = merchantId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantTradeRequestDTO;
    }



    /* 17 */
    protected MerchantTradeRequestDTO(MerchantTradeRequestDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
    }

    public static MerchantTradeRequestDTOBuilder<?, ?> builder() {
        return new MerchantTradeRequestDTOBuilderImpl();
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
        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private Long merchantId;

        protected abstract B self();

        public abstract C build();

    }

    public MerchantTradeRequestDTO(Long merchantId) {
        /* 18 */
        this.merchantId = merchantId;

    }



    public MerchantTradeRequestDTO() {
    }



    public Long getMerchantId() {
        /* 24 */
        return this.merchantId;

    }

}


