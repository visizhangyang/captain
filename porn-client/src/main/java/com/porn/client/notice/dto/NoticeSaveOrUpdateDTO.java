package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class NoticeSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("置顶标识, TopFlagEnum")
    private Integer topFlag;

    @ApiModelProperty("语言类型, LangTypeEnum")
    private Integer langType;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容(富文本)")
    private String content;

    protected NoticeSaveOrUpdateDTO(NoticeSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.topFlag = b.topFlag;
        this.langType = b.langType;
        this.title = b.title;
        this.content = b.content;
    }

    public NoticeSaveOrUpdateDTO(Integer topFlag, Integer langType, String title, String content) {

        this.topFlag = topFlag;
        this.langType = langType;
        this.title = title;
        this.content = content;

    }

    public NoticeSaveOrUpdateDTO() {
    }

    public static NoticeSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new NoticeSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeSaveOrUpdateDTO;
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

    public String getTitle() {

        return this.title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {

        return this.content;

    }

    public void setContent(String content) {
        this.content = content;
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
        private String title;
        private String content;

        public B topFlag(Integer topFlag) {
            this.topFlag = topFlag;
            return self();
        }

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

}

