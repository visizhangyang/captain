package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class NoticeAccountQueryDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("公告ID")
    private Long noticeId;

    @ApiModelProperty("公告ID列表")
    private List<Long> noticeIdList;

    protected NoticeAccountQueryDTO(NoticeAccountQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.noticeId = b.noticeId;
        this.noticeIdList = b.noticeIdList;
    }

    public NoticeAccountQueryDTO(Long accountId, Long noticeId, List<Long> noticeIdList) {

        this.accountId = accountId;
        this.noticeId = noticeId;
        this.noticeIdList = noticeIdList;

    }

    public NoticeAccountQueryDTO() {
    }

    public static NoticeAccountQueryDTOBuilder<?, ?> builder() {
        return new NoticeAccountQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeAccountQueryDTO;
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

    public List<Long> getNoticeIdList() {

        return this.noticeIdList;

    }

    public void setNoticeIdList(List<Long> noticeIdList) {
        this.noticeIdList = noticeIdList;
    }

    private static final class NoticeAccountQueryDTOBuilderImpl extends NoticeAccountQueryDTOBuilder<NoticeAccountQueryDTO, NoticeAccountQueryDTOBuilderImpl> {
        private NoticeAccountQueryDTOBuilderImpl() {
        }

        protected NoticeAccountQueryDTOBuilderImpl self() {
            return this;
        }

        public NoticeAccountQueryDTO build() {
            return new NoticeAccountQueryDTO(this);
        }
    }

    public static abstract class NoticeAccountQueryDTOBuilder<C extends NoticeAccountQueryDTO, B extends NoticeAccountQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private Long noticeId;
        private List<Long> noticeIdList;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B noticeId(Long noticeId) {
            this.noticeId = noticeId;
            return self();
        }

        public B noticeIdList(List<Long> noticeIdList) {
            this.noticeIdList = noticeIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

