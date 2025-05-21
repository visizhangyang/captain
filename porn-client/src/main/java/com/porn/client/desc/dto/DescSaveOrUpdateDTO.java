
package com.porn.client.desc.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;


public class DescSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("描述类型, DescTypeEnum")
    private Integer descType;

    @ApiModelProperty("描述文本")
    private String descText;

    @ApiModelProperty("语言类型")
    private Integer langType;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    public void setDescType(Integer descType) {
        this.descType = descType;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    protected boolean canEqual(Object other) {
        return other instanceof DescSaveOrUpdateDTO;
    }



    protected DescSaveOrUpdateDTO(DescSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.descType = b.descType;
        this.descText = b.descText;
        this.langType = b.langType;
        this.sortNo = b.sortNo;
    }

    public static DescSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new DescSaveOrUpdateDTOBuilderImpl();
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

        public B descType(Integer descType) {
            this.descType = descType;
            return self();
        }

        private Integer langType;
        private Integer sortNo;

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

    public DescSaveOrUpdateDTO(Integer descType, String descText, Integer langType, Integer sortNo) {
        this.descType = descType;
        this.descText = descText;
        this.langType = langType;
        this.sortNo = sortNo;
    }

    public DescSaveOrUpdateDTO() {
    }

    public Integer getDescType() {
        return this.descType;
    }

    public String getDescText() {
        return this.descText;
    }

    public Integer getLangType() {
        return this.langType;
    }

    public Integer getSortNo() {
        return this.sortNo;
    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/desc/dto/DescSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */