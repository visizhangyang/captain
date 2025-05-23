package com.porn.client.goods.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class GoodsQueryDTO extends BaseDTO {

    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("商户名称")
    private String merchantName;

    protected GoodsQueryDTO(GoodsQueryDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.merchantName = b.merchantName;
    }

    public GoodsQueryDTO(Long merchantId, String merchantName) {

        this.merchantId = merchantId;
        this.merchantName = merchantName;

    }

    public GoodsQueryDTO() {
    }

    public static GoodsQueryDTOBuilder<?, ?> builder() {
        return new GoodsQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsQueryDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return this.merchantName;

    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    private static final class GoodsQueryDTOBuilderImpl extends GoodsQueryDTOBuilder<GoodsQueryDTO, GoodsQueryDTOBuilderImpl> {
        private GoodsQueryDTOBuilderImpl() {
        }

        protected GoodsQueryDTOBuilderImpl self() {
            return this;
        }

        public GoodsQueryDTO build() {
            return new GoodsQueryDTO(this);
        }
    }

    public static abstract class GoodsQueryDTOBuilder<C extends GoodsQueryDTO, B extends GoodsQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;
        private String merchantName;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B merchantName(String merchantName) {
            this.merchantName = merchantName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

