package com.porn.client.richtext.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class RichTextQueryDTO extends BaseDTO {

    @ApiModelProperty("类型, RichTextTypeEnum")
    private Integer type;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("语言名称")
    private String langTypeName;

    protected RichTextQueryDTO(RichTextQueryDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public RichTextQueryDTO(Integer type, Integer langType, String langTypeName) {

        this.type = type;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }

    public RichTextQueryDTO() {
    }

    public static RichTextQueryDTOBuilder<?, ?> builder() {
        return new RichTextQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RichTextQueryDTO;
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

    public String getLangTypeName() {

        return this.langTypeName;

    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }

    private static final class RichTextQueryDTOBuilderImpl extends RichTextQueryDTOBuilder<RichTextQueryDTO, RichTextQueryDTOBuilderImpl> {
        private RichTextQueryDTOBuilderImpl() {
        }

        protected RichTextQueryDTOBuilderImpl self() {
            return this;
        }

        public RichTextQueryDTO build() {
            return new RichTextQueryDTO(this);
        }
    }

    public static abstract class RichTextQueryDTOBuilder<C extends RichTextQueryDTO, B extends RichTextQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer type;
        private Integer langType;
        private String langTypeName;

        public B type(Integer type) {
            this.type = type;
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

