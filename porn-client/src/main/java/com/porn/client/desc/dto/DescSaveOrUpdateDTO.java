package com.porn.client.desc.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;


public class DescSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("描述类型, DescTypeEnum")
    private Integer descType;

    @ApiModelProperty("描述文本")
    private String descText;

    @ApiModelProperty("语言类型")
    private Integer langType;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    protected DescSaveOrUpdateDTO(DescSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.descType = b.descType;
        this.descText = b.descText;
        this.langType = b.langType;
        this.sortNo = b.sortNo;
    }

    public DescSaveOrUpdateDTO(Integer descType, String descText, Integer langType, Integer sortNo) {
        this.descType = descType;
        this.descText = descText;
        this.langType = langType;
        this.sortNo = sortNo;
    }

    public DescSaveOrUpdateDTO() {
    }

    public static DescSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new DescSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof DescSaveOrUpdateDTO;
    }

    public Integer getDescType() {
        return this.descType;
    }

    public void setDescType(Integer descType) {
        this.descType = descType;
    }

    public String getDescText() {
        return this.descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public Integer getLangType() {
        return this.langType;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public Integer getSortNo() {
        return this.sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    private static final class DescSaveOrUpdateDTOBuilderImpl extends DescSaveOrUpdateDTOBuilder<DescSaveOrUpdateDTO, DescSaveOrUpdateDTOBuilderImpl> {
        private DescSaveOrUpdateDTOBuilderImpl() {
        }

        protected DescSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public DescSaveOrUpdateDTO build() {
            return new DescSaveOrUpdateDTO(this);
        }
    }

    public static abstract class DescSaveOrUpdateDTOBuilder<C extends DescSaveOrUpdateDTO, B extends DescSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer descType;
        private String descText;
        private Integer langType;
        private Integer sortNo;

        public B descType(Integer descType) {
            this.descType = descType;
            return self();
        }

        public B descText(String descText) {
            this.descText = descText;
            return self();
        }

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B sortNo(Integer sortNo) {
            this.sortNo = sortNo;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}


