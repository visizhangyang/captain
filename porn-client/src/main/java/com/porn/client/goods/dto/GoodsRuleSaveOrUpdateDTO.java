package com.porn.client.goods.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected GoodsRuleSaveOrUpdateDTO(GoodsRuleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.minGoodsCount = b.minGoodsCount;
        this.maxGoodsCount = b.maxGoodsCount;
        this.minAmount = b.minAmount;
        this.maxAmount = b.maxAmount;
        this.status = b.status;
    }

    public GoodsRuleSaveOrUpdateDTO(Long merchantId, Integer minGoodsCount, Integer maxGoodsCount, BigDecimal minAmount, BigDecimal maxAmount, Integer status) {

        this.merchantId = merchantId;
        this.minGoodsCount = minGoodsCount;
        this.maxGoodsCount = maxGoodsCount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.status = status;

    }

    public GoodsRuleSaveOrUpdateDTO() {
    }

    public static GoodsRuleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new GoodsRuleSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsRuleSaveOrUpdateDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMinGoodsCount() {

        return this.minGoodsCount;

    }

    public void setMinGoodsCount(Integer minGoodsCount) {
        this.minGoodsCount = minGoodsCount;
    }

    public Integer getMaxGoodsCount() {

        return this.maxGoodsCount;

    }

    public void setMaxGoodsCount(Integer maxGoodsCount) {
        this.maxGoodsCount = maxGoodsCount;
    }

    public BigDecimal getMinAmount() {

        return this.minAmount;

    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {

        return this.maxAmount;

    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
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
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
        private Integer status;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

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

}

