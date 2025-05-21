
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class MerchantDescQueryDTO extends BaseDTO {

    @ApiModelProperty("商户ID")
     private Long merchantId;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("语言名称")
     private String langTypeName;


    /* 15 */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantDescQueryDTO;
    }



    /* 16 */
    protected MerchantDescQueryDTO(MerchantDescQueryDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public static MerchantDescQueryDTOBuilder<?, ?> builder() {
        return new MerchantDescQueryDTOBuilderImpl();
    }

    private static final class MerchantDescQueryDTOBuilderImpl extends MerchantDescQueryDTOBuilder<MerchantDescQueryDTO, MerchantDescQueryDTOBuilderImpl> {
        private MerchantDescQueryDTOBuilderImpl() {
        }

        protected MerchantDescQueryDTOBuilderImpl self() {
            return this;
        }

        public MerchantDescQueryDTO build() {
            return new MerchantDescQueryDTO(this);
        }
    }

    public static abstract class MerchantDescQueryDTOBuilder<C extends MerchantDescQueryDTO, B extends MerchantDescQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private Integer langType;
        private String langTypeName;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B langTypeName(String langTypeName) {
            this.langTypeName = langTypeName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public MerchantDescQueryDTO(Long merchantId, Integer langType, String langTypeName) {
        /* 17 */
        this.merchantId = merchantId;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }


    public MerchantDescQueryDTO() {
    }



    public Long getMerchantId() {
        /* 22 */
        return this.merchantId;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }


    public String getLangTypeName() {
        /* 28 */
        return this.langTypeName;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/dto/MerchantDescQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */