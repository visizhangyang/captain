
package com.porn.client.richtext.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class RichTextSaveOrUpdateDTO extends BaseDTO {
    
    @ApiModelProperty("类型, RichTextTypeEnum")
     private Integer type;
    
    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;
    
    @ApiModelProperty("大字符串")
     private String richText;

    
    /* 15 */
    public void setType(Integer type) {
        this.type = type;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RichTextSaveOrUpdateDTO;
    }



    /* 16 */
    protected RichTextSaveOrUpdateDTO(RichTextSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.type = b.type;
        this.langType = b.langType;
        this.richText = b.richText;
    }

    public static RichTextSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RichTextSaveOrUpdateDTOBuilderImpl();
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

        public B type(Integer type) {
            this.type = type;
            return self();
        }

        private Integer langType;
        private String richText;

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

    public RichTextSaveOrUpdateDTO(Integer type, Integer langType, String richText) {
        /* 17 */
        this.type = type;
        this.langType = langType;
        this.richText = richText;
        
    }

    
    public RichTextSaveOrUpdateDTO() {
    }

    
    
    public Integer getType() {
        /* 22 */
        return this.type;
        
    }

    
    public Integer getLangType() {
        /* 25 */
        return this.langType;
        
    }

    
    public String getRichText() {
        /* 28 */
        return this.richText;
        
    }
    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/richtext/dto/RichTextSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */