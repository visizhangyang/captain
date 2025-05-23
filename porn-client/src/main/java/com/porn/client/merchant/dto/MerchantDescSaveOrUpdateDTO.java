package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class MerchantDescSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("商户ID")
    private Long merchantId;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("内容")
    private String content;

    protected MerchantDescSaveOrUpdateDTO(MerchantDescSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.langType = b.langType;
        this.content = b.content;
    }

    public MerchantDescSaveOrUpdateDTO(Long merchantId, Integer langType, String content) {

        this.merchantId = merchantId;
        this.langType = langType;
        this.content = content;

    }

    public MerchantDescSaveOrUpdateDTO() {
    }

    public static MerchantDescSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new MerchantDescSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantDescSaveOrUpdateDTO;
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

    public String getContent() {

        return this.content;

    }

    public void setContent(String content) {
        this.content = content;
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
        private Integer langType;
        private String content;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

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

}

