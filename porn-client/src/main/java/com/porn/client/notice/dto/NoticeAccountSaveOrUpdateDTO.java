package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class NoticeAccountSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("公告ID")
    private Long noticeId;

    protected NoticeAccountSaveOrUpdateDTO(NoticeAccountSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.noticeId = b.noticeId;
    }

    public NoticeAccountSaveOrUpdateDTO(Long accountId, Long noticeId) {

        this.accountId = accountId;
        this.noticeId = noticeId;

    }

    public NoticeAccountSaveOrUpdateDTO() {
    }

    public static NoticeAccountSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new NoticeAccountSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeAccountSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    public Long getNoticeId() {

        return this.noticeId;

    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    private static final class NoticeAccountSaveOrUpdateDTOBuilderImpl extends NoticeAccountSaveOrUpdateDTOBuilder<NoticeAccountSaveOrUpdateDTO, NoticeAccountSaveOrUpdateDTOBuilderImpl> {
        private NoticeAccountSaveOrUpdateDTOBuilderImpl() {
        }

        protected NoticeAccountSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public NoticeAccountSaveOrUpdateDTO build() {
            return new NoticeAccountSaveOrUpdateDTO(this);
        }
    }

    public static abstract class NoticeAccountSaveOrUpdateDTOBuilder<C extends NoticeAccountSaveOrUpdateDTO, B extends NoticeAccountSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Long noticeId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B noticeId(Long noticeId) {
            this.noticeId = noticeId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

