package com.porn.client.richtext.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RichTextSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("类型, RichTextTypeEnum")
    private Integer type;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("大字符串")
    private String richText;

    protected RichTextSaveOrUpdateDTO(RichTextSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.langType = b.langType;
        this.richText = b.richText;
    }

    public RichTextSaveOrUpdateDTO(Integer type, Integer langType, String richText) {

        this.type = type;
        this.langType = langType;
        this.richText = richText;

    }

    public RichTextSaveOrUpdateDTO() {
    }

    public static RichTextSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RichTextSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RichTextSaveOrUpdateDTO;
    }

    public Integer getType() {

        return this.type;

    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLangType() {

        return this.langType;

    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public String getRichText() {

        return this.richText;

    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    private static final class RichTextSaveOrUpdateDTOBuilderImpl extends RichTextSaveOrUpdateDTOBuilder<RichTextSaveOrUpdateDTO, RichTextSaveOrUpdateDTOBuilderImpl> {
        private RichTextSaveOrUpdateDTOBuilderImpl() {
        }

        protected RichTextSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RichTextSaveOrUpdateDTO build() {
            return new RichTextSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RichTextSaveOrUpdateDTOBuilder<C extends RichTextSaveOrUpdateDTO, B extends RichTextSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;
        private Integer langType;
        private String richText;

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B richText(String richText) {
            this.richText = richText;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

