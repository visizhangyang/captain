
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class NoticeQueryDTO extends BaseDTO {

    @ApiModelProperty("置顶标识, TopFlagEnum")
     private Integer topFlag;

    @ApiModelProperty("语言类型, LangTypeEnum")
     private Integer langType;

    @ApiModelProperty("标题")
     private String lkTitle;


    /* 15 */
    public void setTopFlag(Integer topFlag) {
        this.topFlag = topFlag;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLkTitle(String lkTitle) {
        this.lkTitle = lkTitle;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeQueryDTO;
    }



    /* 16 */
    protected NoticeQueryDTO(NoticeQueryDTOBuilder<?, ?> b) {
        super(b);
        this.topFlag = b.topFlag;
        this.langType = b.langType;
        this.lkTitle = b.lkTitle;
    }

    public static NoticeQueryDTOBuilder<?, ?> builder() {
        return new NoticeQueryDTOBuilderImpl();
    }

    private static final class NoticeQueryDTOBuilderImpl extends NoticeQueryDTOBuilder<NoticeQueryDTO, NoticeQueryDTOBuilderImpl> {
        private NoticeQueryDTOBuilderImpl() {
        }

        protected NoticeQueryDTOBuilderImpl self() {
            return this;
        }

        public NoticeQueryDTO build() {
            return new NoticeQueryDTO(this);
        }
    }

    public static abstract class NoticeQueryDTOBuilder<C extends NoticeQueryDTO, B extends NoticeQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer topFlag;

        public B topFlag(Integer topFlag) {
            this.topFlag = topFlag;
            return self();
        }

        private Integer langType;
        private String lkTitle;

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NoticeQueryDTO(Integer topFlag, Integer langType, String lkTitle) {
        /* 17 */
        this.topFlag = topFlag;
        this.langType = langType;
        this.lkTitle = lkTitle;

    }


    public NoticeQueryDTO() {
    }



    public Integer getTopFlag() {
        /* 22 */
        return this.topFlag;

    }


    public Integer getLangType() {
        /* 25 */
        return this.langType;

    }


    public String getLkTitle() {
        /* 28 */
        return this.lkTitle;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/dto/NoticeQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */