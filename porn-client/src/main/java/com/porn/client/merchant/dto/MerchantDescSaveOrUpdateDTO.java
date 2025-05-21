
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class MerchantDescSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("商户ID")
     private Long merchantId;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("内容")
     private String content;


    /* 15 */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setContent(String content) {
        this.content = content;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantDescSaveOrUpdateDTO;
    }



    /* 16 */
    protected MerchantDescSaveOrUpdateDTO(MerchantDescSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.langType = b.langType;
        this.content = b.content;
    }

    public static MerchantDescSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new MerchantDescSaveOrUpdateDTOBuilderImpl();
    }

    private static final class MerchantDescSaveOrUpdateDTOBuilderImpl extends MerchantDescSaveOrUpdateDTOBuilder<MerchantDescSaveOrUpdateDTO, MerchantDescSaveOrUpdateDTOBuilderImpl> {
        private MerchantDescSaveOrUpdateDTOBuilderImpl() {
        }

        protected MerchantDescSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public MerchantDescSaveOrUpdateDTO build() {
            return new MerchantDescSaveOrUpdateDTO(this);
        }
    }

    public static abstract class MerchantDescSaveOrUpdateDTOBuilder<C extends MerchantDescSaveOrUpdateDTO, B extends MerchantDescSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private Integer langType;
        private String content;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B content(String content) {
            this.content = content;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public MerchantDescSaveOrUpdateDTO(Long merchantId, Integer langType, String content) {
        /* 17 */
        this.merchantId = merchantId;
        this.langType = langType;
        this.content = content;

    }


    public MerchantDescSaveOrUpdateDTO() {
    }



    public Long getMerchantId() {
        /* 22 */
        return this.merchantId;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }


    public String getContent() {
        /* 28 */
        return this.content;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/merchant/dto/MerchantDescSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */