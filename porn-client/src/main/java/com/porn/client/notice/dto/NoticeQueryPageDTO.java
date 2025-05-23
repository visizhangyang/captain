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

    protected NoticeQueryPageDTO(NoticeQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.topFlag = b.topFlag;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
        this.lkTitle = b.lkTitle;
    }

    public NoticeQueryPageDTO(Integer topFlag, Integer langType, String langTypeName, String lkTitle) {

        this.topFlag = topFlag;
        this.langType = langType;
        this.langTypeName = langTypeName;
        this.lkTitle = lkTitle;

    }

    public NoticeQueryPageDTO() {
    }

    public static NoticeQueryPageDTOBuilder<?, ?> builder() {
        return new NoticeQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeQueryPageDTO;
    }

    public Integer getTopFlag() {

        return this.topFlag;

    }

    public void setTopFlag(Integer topFlag) {
        this.topFlag = topFlag;
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

    public String getLkTitle() {

        return this.lkTitle;

    }

    public void setLkTitle(String lkTitle) {
        this.lkTitle = lkTitle;
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
        private String langTypeName;
        private String lkTitle;

        public B topFlag(Integer topFlag) {
            this.topFlag = topFlag;
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

        public B lkTitle(String lkTitle) {
            this.lkTitle = lkTitle;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

