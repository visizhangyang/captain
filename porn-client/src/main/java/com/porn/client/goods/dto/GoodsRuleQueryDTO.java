
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class GoodsRuleQueryDTO extends BaseDTO {
    
    @ApiModelProperty("商户id")
     private Long merchantId;
    
    @ApiModelProperty("状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
     private Integer status;

    
    
    public void setMerchantId(Long merchantId) {
        /* 15 */
        this.merchantId = merchantId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsRuleQueryDTO;
    }



    /* 16 */
    protected GoodsRuleQueryDTO(GoodsRuleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.status = b.status;
    }

    public static GoodsRuleQueryDTOBuilder<?, ?> builder() {
        return new GoodsRuleQueryDTOBuilderImpl();
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

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private Integer status;

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public GoodsRuleQueryDTO(Long merchantId, Integer status) {
        /* 17 */
        this.merchantId = merchantId;
        this.status = status;
        
    }

    
    public GoodsRuleQueryDTO() {
    }

    
    
    public Long getMerchantId() {
        /* 22 */
        return this.merchantId;
        
    }

    
    public Integer getStatus() {
        /* 25 */
        return this.status;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/goods/dto/GoodsRuleQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */