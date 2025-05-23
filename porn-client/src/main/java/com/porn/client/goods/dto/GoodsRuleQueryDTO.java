package com.porn.client.goods.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class GoodsRuleQueryDTO extends BaseDTO {

    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

    protected GoodsRuleQueryDTO(GoodsRuleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.status = b.status;
    }

    public GoodsRuleQueryDTO(Long merchantId, Integer status) {

        this.merchantId = merchantId;
        this.status = status;

    }

    public GoodsRuleQueryDTO() {
    }

    public static GoodsRuleQueryDTOBuilder<?, ?> builder() {
        return new GoodsRuleQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsRuleQueryDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {

        this.merchantId = merchantId;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private static final class GoodsRuleQueryDTOBuilderImpl extends GoodsRuleQueryDTOBuilder<GoodsRuleQueryDTO, GoodsRuleQueryDTOBuilderImpl> {
        private GoodsRuleQueryDTOBuilderImpl() {
        }

        protected GoodsRuleQueryDTOBuilderImpl self() {
            return this;
        }

        public GoodsRuleQueryDTO build() {
            return new GoodsRuleQueryDTO(this);
        }
    }

    public static abstract class GoodsRuleQueryDTOBuilder<C extends GoodsRuleQueryDTO, B extends GoodsRuleQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;
        private Integer status;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
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

