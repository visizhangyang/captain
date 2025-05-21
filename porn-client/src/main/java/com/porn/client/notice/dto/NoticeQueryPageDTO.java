
package com.porn.client.notice.dto;
import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;




 public class NoticeQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("置顶标识, TopFlagEnum")
     private Integer topFlag;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("语言类型")
     private String langTypeName;

    @ApiModelProperty("标题")
     private String lkTitle;


    /* 15 */
    public void setTopFlag(Integer topFlag) {
        this.topFlag = topFlag;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }

    public void setLkTitle(String lkTitle) {
        this.lkTitle = lkTitle;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeQueryPageDTO;
    }



    /* 16 */
    protected NoticeQueryPageDTO(NoticeQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.topFlag = b.topFlag;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
        this.lkTitle = b.lkTitle;
    }

    public static NoticeQueryPageDTOBuilder<?, ?> builder() {
        return new NoticeQueryPageDTOBuilderImpl();
    }

    private static final class NoticeQueryPageDTOBuilderImpl extends NoticeQueryPageDTOBuilder<NoticeQueryPageDTO, NoticeQueryPageDTOBuilderImpl> {
        private NoticeQueryPageDTOBuilderImpl() {
        }

        protected NoticeQueryPageDTOBuilderImpl self() {
            return this;
        }

        public NoticeQueryPageDTO build() {
            return new NoticeQueryPageDTO(this);
        }
    }

    public static abstract class NoticeQueryPageDTOBuilder<C extends NoticeQueryPageDTO, B extends NoticeQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Integer topFlag;
        private Integer langType;

        public B topFlag(Integer topFlag) {
            this.topFlag = topFlag;
            return self();
        }

        private String langTypeName;
        private String lkTitle;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B langTypeName(String langTypeName) {
            this.langTypeName = langTypeName;
            return self();
        }

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NoticeQueryPageDTO(Integer topFlag, Integer langType, String langTypeName, String lkTitle) {
        /* 17 */
        this.topFlag = topFlag;
        this.langType = langType;
        this.langTypeName = langTypeName;
        this.lkTitle = lkTitle;

    }


    public NoticeQueryPageDTO() {
    }



    public Integer getTopFlag() {
        /* 22 */
        return this.topFlag;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }


    public String getLangTypeName() {
        /* 28 */
        return this.langTypeName;

    }


    public String getLkTitle() {
        /* 31 */
        return this.lkTitle;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/dto/NoticeQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */