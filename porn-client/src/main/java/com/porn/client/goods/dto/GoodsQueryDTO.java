
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class GoodsQueryDTO extends BaseDTO {
    
    @ApiModelProperty("商户id")
     private Long merchantId;
    
    @ApiModelProperty("商户名称")
     private String merchantName;

    
    
    public void setMerchantId(Long merchantId) {
        /* 15 */
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsQueryDTO;
    }



    /* 16 */
    protected GoodsQueryDTO(GoodsQueryDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.merchantName = b.merchantName;
    }

    public static GoodsQueryDTOBuilder<?, ?> builder() {
        return new GoodsQueryDTOBuilderImpl();
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

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private String merchantName;

        public B merchantName(String merchantName) {
            this.merchantName = merchantName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public GoodsQueryDTO(Long merchantId, String merchantName) {
        /* 17 */
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        
    }

    
    public GoodsQueryDTO() {
    }

    
    
    public Long getMerchantId() {
        /* 22 */
        return this.merchantId;
        
    }

    
    public String getMerchantName() {
        /* 25 */
        return this.merchantName;
        
    }
    
}


