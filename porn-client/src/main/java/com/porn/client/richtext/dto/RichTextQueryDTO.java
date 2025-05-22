
package com.porn.client.richtext.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RichTextQueryDTO extends BaseDTO {

    @ApiModelProperty("类型, RichTextTypeEnum")
     private Integer type;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("语言名称")
     private String langTypeName;


    /* 15 */
    public void setType(Integer type) {
        this.type = type;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RichTextQueryDTO;
    }



    /* 16 */
    protected RichTextQueryDTO(RichTextQueryDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
    }

    public static RichTextQueryDTOBuilder<?, ?> builder() {
        return new RichTextQueryDTOBuilderImpl();
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

        public B type(Integer type) {
            this.type = type;
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

    public RichTextQueryDTO(Integer type, Integer langType, String langTypeName) {
        /* 17 */
        this.type = type;
        this.langType = langType;
        this.langTypeName = langTypeName;

    }


    public RichTextQueryDTO() {
    }



    public Integer getType() {
        /* 22 */
        return this.type;

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


