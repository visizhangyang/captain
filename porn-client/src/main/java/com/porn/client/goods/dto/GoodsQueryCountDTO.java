
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class GoodsQueryCountDTO extends BaseDTO {

    @ApiModelProperty("商户id")
     private Long merchantId;

    @ApiModelProperty("商品状态, GoodsStatusEnum")
     private Integer goodsStatus;



    public void setMerchantId(Long merchantId) {
        /* 15 */
        this.merchantId = merchantId;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsQueryCountDTO;
    }



    /* 16 */
    protected GoodsQueryCountDTO(GoodsQueryCountDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.goodsStatus = b.goodsStatus;
    }

    public static GoodsQueryCountDTOBuilder<?, ?> builder() {
        return new GoodsQueryCountDTOBuilderImpl();
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

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private Integer goodsStatus;

        public B goodsStatus(Integer goodsStatus) {
            this.goodsStatus = goodsStatus;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public GoodsQueryCountDTO(Long merchantId, Integer goodsStatus) {
        /* 17 */
        this.merchantId = merchantId;
        this.goodsStatus = goodsStatus;

    }


    public GoodsQueryCountDTO() {
    }



    public Long getMerchantId() {
        /* 22 */
        return this.merchantId;

    }


    public Integer getGoodsStatus() {
        /* 25 */
        return this.goodsStatus;

    }

}


