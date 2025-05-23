package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class NoticeQueryDTO extends BaseDTO {

    @ApiModelProperty("置顶标识, TopFlagEnum")
    private Integer topFlag;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("标题")
    private String lkTitle;

    protected NoticeQueryDTO(NoticeQueryDTOBuilder<?, ?> b) {
        super(b);
        this.topFlag = b.topFlag;
        this.langType = b.langType;
        this.lkTitle = b.lkTitle;
    }

    public NoticeQueryDTO(Integer topFlag, Integer langType, String lkTitle) {

        this.topFlag = topFlag;
        this.langType = langType;
        this.lkTitle = lkTitle;

    }

    public NoticeQueryDTO() {
    }

    public static NoticeQueryDTOBuilder<?, ?> builder() {
        return new NoticeQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeQueryDTO;
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

    public String getLkTitle() {

        return this.lkTitle;

    }

    public void setLkTitle(String lkTitle) {
        this.lkTitle = lkTitle;
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
        private Integer langType;
        private String lkTitle;

        public B topFlag(Integer topFlag) {
            this.topFlag = topFlag;
            return self();
        }

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

}

