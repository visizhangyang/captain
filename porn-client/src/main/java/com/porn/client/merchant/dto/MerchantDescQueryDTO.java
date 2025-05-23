package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class MerchantDescQueryDTO extends BaseDTO {

    @ApiModelProperty("商户ID")
    private Long merchantId;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("语言名称")
    private String langTypeName;

    protected MerchantDescQueryDTO(MerchantDescQueryDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public MerchantDescQueryDTO(Long merchantId, Integer langType, String langTypeName) {

        this.merchantId = merchantId;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }

    public MerchantDescQueryDTO() {
    }

    public static MerchantDescQueryDTOBuilder<?, ?> builder() {
        return new MerchantDescQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantDescQueryDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getLangType() {

        return this.langType;

    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public String getLangTypeName() {

        return this.langTypeName;

    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
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
        private Integer langType;
        private String langTypeName;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

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

}

