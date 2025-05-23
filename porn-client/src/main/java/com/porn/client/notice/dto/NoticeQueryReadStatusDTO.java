package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class NoticeQueryReadStatusDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("读取类型, 0-未读, 1-已读")
    private Integer readType;

    protected NoticeQueryReadStatusDTO(NoticeQueryReadStatusDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.readType = b.readType;
    }

    public NoticeQueryReadStatusDTO(Long accountId, Integer readType) {

        this.accountId = accountId;
        this.readType = readType;

    }

    public NoticeQueryReadStatusDTO() {
    }

    public static NoticeQueryReadStatusDTOBuilder<?, ?> builder() {
        return new NoticeQueryReadStatusDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeQueryReadStatusDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    public Integer getReadType() {

        return this.readType;

    }

    public void setReadType(Integer readType) {
        this.readType = readType;
    }

    private static final class NoticeQueryReadStatusDTOBuilderImpl extends NoticeQueryReadStatusDTOBuilder<NoticeQueryReadStatusDTO, NoticeQueryReadStatusDTOBuilderImpl> {
        private NoticeQueryReadStatusDTOBuilderImpl() {
        }

        protected NoticeQueryReadStatusDTOBuilderImpl self() {
            return this;
        }

        public NoticeQueryReadStatusDTO build() {
            return new NoticeQueryReadStatusDTO(this);
        }
    }

    public static abstract class NoticeQueryReadStatusDTOBuilder<C extends NoticeQueryReadStatusDTO, B extends NoticeQueryReadStatusDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Integer readType;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B readType(Integer readType) {
            this.readType = readType;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

