
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


 public class NoticeSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("置顶标识, TopFlagEnum")
     private Integer topFlag;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("标题")
     private String title;

    @ApiModelProperty("内容(富文本)")
     private String content;


    /* 15 */
    public void setTopFlag(Integer topFlag) {
        this.topFlag = topFlag;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeSaveOrUpdateDTO;
    }



    /* 16 */
    protected NoticeSaveOrUpdateDTO(NoticeSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.topFlag = b.topFlag;
        this.langType = b.langType;
        this.title = b.title;
        this.content = b.content;
    }

    public static NoticeSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new NoticeSaveOrUpdateDTOBuilderImpl();
    }

    private static final class NoticeSaveOrUpdateDTOBuilderImpl extends NoticeSaveOrUpdateDTOBuilder<NoticeSaveOrUpdateDTO, NoticeSaveOrUpdateDTOBuilderImpl> {
        private NoticeSaveOrUpdateDTOBuilderImpl() {
        }

        protected NoticeSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public NoticeSaveOrUpdateDTO build() {
            return new NoticeSaveOrUpdateDTO(this);
        }
    }

    public static abstract class NoticeSaveOrUpdateDTOBuilder<C extends NoticeSaveOrUpdateDTO, B extends NoticeSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer topFlag;
        private Integer langType;

        public B topFlag(Integer topFlag) {
            this.topFlag = topFlag;
            return self();
        }

        private String title;
        private String content;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B title(String title) {
            this.title = title;
            return self();
        }

        public B content(String content) {
            this.content = content;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NoticeSaveOrUpdateDTO(Integer topFlag, Integer langType, String title, String content) {
        /* 17 */
        this.topFlag = topFlag;
        this.langType = langType;
        this.title = title;
        this.content = content;

    }


    public NoticeSaveOrUpdateDTO() {
    }



    public Integer getTopFlag() {
        /* 22 */
        return this.topFlag;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }


    public String getTitle() {
        /* 28 */
        return this.title;

    }


    public String getContent() {
        /* 31 */
        return this.content;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/dto/NoticeSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */