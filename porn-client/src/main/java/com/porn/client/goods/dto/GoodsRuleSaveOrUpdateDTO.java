
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;

 public class GoodsRuleSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("商户id")
     private Long merchantId;

    @ApiModelProperty("最小商品数量")
     private Integer minGoodsCount;

    @ApiModelProperty("最大商品数量")
     private Integer maxGoodsCount;

    @ApiModelProperty("最小金额")
     private BigDecimal minAmount;

    @ApiModelProperty("最大金额")
     private BigDecimal maxAmount;

    @ApiModelProperty("状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;


    /* 16 */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setMinGoodsCount(Integer minGoodsCount) {
        this.minGoodsCount = minGoodsCount;
    }

    public void setMaxGoodsCount(Integer maxGoodsCount) {
        this.maxGoodsCount = maxGoodsCount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsRuleSaveOrUpdateDTO;
    }



    /* 17 */
    protected GoodsRuleSaveOrUpdateDTO(GoodsRuleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.minGoodsCount = b.minGoodsCount;
        this.maxGoodsCount = b.maxGoodsCount;
        this.minAmount = b.minAmount;
        this.maxAmount = b.maxAmount;
        this.status = b.status;
    }

    public static GoodsRuleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new GoodsRuleSaveOrUpdateDTOBuilderImpl();
    }

    private static final class GoodsRuleSaveOrUpdateDTOBuilderImpl extends GoodsRuleSaveOrUpdateDTOBuilder<GoodsRuleSaveOrUpdateDTO, GoodsRuleSaveOrUpdateDTOBuilderImpl> {
        private GoodsRuleSaveOrUpdateDTOBuilderImpl() {
        }

        protected GoodsRuleSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public GoodsRuleSaveOrUpdateDTO build() {
            return new GoodsRuleSaveOrUpdateDTO(this);
        }
    }

    public static abstract class GoodsRuleSaveOrUpdateDTOBuilder<C extends GoodsRuleSaveOrUpdateDTO, B extends GoodsRuleSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;
        private Integer minGoodsCount;
        private Integer maxGoodsCount;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private BigDecimal minAmount;
        private BigDecimal maxAmount;
        private Integer status;

        public B minGoodsCount(Integer minGoodsCount) {
            this.minGoodsCount = minGoodsCount;
            return self();
        }

        public B maxGoodsCount(Integer maxGoodsCount) {
            this.maxGoodsCount = maxGoodsCount;
            return self();
        }

        public B minAmount(BigDecimal minAmount) {
            this.minAmount = minAmount;
            return self();
        }

        public B maxAmount(BigDecimal maxAmount) {
            this.maxAmount = maxAmount;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public GoodsRuleSaveOrUpdateDTO(Long merchantId, Integer minGoodsCount, Integer maxGoodsCount, BigDecimal minAmount, BigDecimal maxAmount, Integer status) {
        /* 18 */
        this.merchantId = merchantId;
        this.minGoodsCount = minGoodsCount;
        this.maxGoodsCount = maxGoodsCount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.status = status;

    }


    public GoodsRuleSaveOrUpdateDTO() {
    }



    public Long getMerchantId() {
        /* 23 */
        return this.merchantId;

    }


    public Integer getMinGoodsCount() {
        /* 26 */
        return this.minGoodsCount;

    }


    public Integer getMaxGoodsCount() {
        /* 29 */
        return this.maxGoodsCount;

    }


    public BigDecimal getMinAmount() {
        /* 32 */
        return this.minAmount;

    }


    public BigDecimal getMaxAmount() {
        /* 35 */
        return this.maxAmount;

    }


    public Integer getStatus() {
        /* 38 */
        return this.status;

    }

}


