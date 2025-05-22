
package com.porn.client.desc.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class DescQueryDTO extends BaseDTO {

    @ApiModelProperty("描述类型, DescTypeEnum")
     private Integer descType;

    @ApiModelProperty("语言类型")
     private Integer langType;

    @ApiModelProperty("语言名称")
     private String langTypeName;


    /* 15 */
    public void setDescType(Integer descType) {
        this.descType = descType;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof DescQueryDTO;
    }



    /* 16 */
    protected DescQueryDTO(DescQueryDTOBuilder<?, ?> b) {
        super(b);
        this.descType = b.descType;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public static DescQueryDTOBuilder<?, ?> builder() {
        return new DescQueryDTOBuilderImpl();
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

        public B descType(Integer descType) {
            this.descType = descType;
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

    public DescQueryDTO(Integer descType, Integer langType, String langTypeName) {
        /* 17 */
        this.descType = descType;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }


    public DescQueryDTO() {
    }



    public Integer getDescType() {
        /* 22 */
        return this.descType;

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


