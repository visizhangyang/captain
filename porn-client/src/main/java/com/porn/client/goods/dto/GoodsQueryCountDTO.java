package com.porn.client.goods.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class GoodsQueryCountDTO extends BaseDTO {

    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("商品状态, GoodsStatusEnum")
    private Integer goodsStatus;

    protected GoodsQueryCountDTO(GoodsQueryCountDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.goodsStatus = b.goodsStatus;
    }

    public GoodsQueryCountDTO(Long merchantId, Integer goodsStatus) {

        this.merchantId = merchantId;
        this.goodsStatus = goodsStatus;

    }

    public GoodsQueryCountDTO() {
    }

    public static GoodsQueryCountDTOBuilder<?, ?> builder() {
        return new GoodsQueryCountDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsQueryCountDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public Integer getGoodsStatus() {

        return this.goodsStatus;

    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    private static final class GoodsQueryCountDTOBuilderImpl extends GoodsQueryCountDTOBuilder<GoodsQueryCountDTO, GoodsQueryCountDTOBuilderImpl> {
        private GoodsQueryCountDTOBuilderImpl() {
        }

        protected GoodsQueryCountDTOBuilderImpl self() {
            return this;
        }

        public GoodsQueryCountDTO build() {
            return new GoodsQueryCountDTO(this);
        }
    }

    public static abstract class GoodsQueryCountDTOBuilder<C extends GoodsQueryCountDTO, B extends GoodsQueryCountDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;
        private Integer goodsStatus;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B goodsStatus(Integer goodsStatus) {
            this.goodsStatus = goodsStatus;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

