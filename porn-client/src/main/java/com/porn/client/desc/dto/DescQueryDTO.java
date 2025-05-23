package com.porn.client.desc.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class DescQueryDTO extends BaseDTO {

    @ApiModelProperty("描述类型, DescTypeEnum")
    private Integer descType;

    @ApiModelProperty("语言类型")
    private Integer langType;

    @ApiModelProperty("语言名称")
    private String langTypeName;

    protected DescQueryDTO(DescQueryDTOBuilder<?, ?> b) {
        super(b);
        this.descType = b.descType;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public DescQueryDTO(Integer descType, Integer langType, String langTypeName) {

        this.descType = descType;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }

    public DescQueryDTO() {
    }

    public static DescQueryDTOBuilder<?, ?> builder() {
        return new DescQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof DescQueryDTO;
    }

    public Integer getDescType() {

        return this.descType;

    }

    public void setDescType(Integer descType) {
        this.descType = descType;
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

    private static final class DescQueryDTOBuilderImpl extends DescQueryDTOBuilder<DescQueryDTO, DescQueryDTOBuilderImpl> {
        private DescQueryDTOBuilderImpl() {
        }

        protected DescQueryDTOBuilderImpl self() {
            return this;
        }

        public DescQueryDTO build() {
            return new DescQueryDTO(this);
        }
    }

    public static abstract class DescQueryDTOBuilder<C extends DescQueryDTO, B extends DescQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer descType;
        private Integer langType;
        private String langTypeName;

        public B descType(Integer descType) {
            this.descType = descType;
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

