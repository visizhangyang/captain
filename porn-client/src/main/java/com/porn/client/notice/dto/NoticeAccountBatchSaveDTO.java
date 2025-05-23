package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class NoticeAccountBatchSaveDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("公告ID列表")
    private List<Long> noticeIdList;

    protected NoticeAccountBatchSaveDTO(NoticeAccountBatchSaveDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.noticeIdList = b.noticeIdList;
    }

    public NoticeAccountBatchSaveDTO(Long accountId, List<Long> noticeIdList) {

        this.accountId = accountId;
        this.noticeIdList = noticeIdList;

    }

    public NoticeAccountBatchSaveDTO() {
    }

    public static NoticeAccountBatchSaveDTOBuilder<?, ?> builder() {
        return new NoticeAccountBatchSaveDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeAccountBatchSaveDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {

        this.accountId = accountId;
    }

    public List<Long> getNoticeIdList() {

        return this.noticeIdList;

    }

    public void setNoticeIdList(List<Long> noticeIdList) {
        this.noticeIdList = noticeIdList;
    }

    private static final class NoticeAccountBatchSaveDTOBuilderImpl extends NoticeAccountBatchSaveDTOBuilder<NoticeAccountBatchSaveDTO, NoticeAccountBatchSaveDTOBuilderImpl> {
        private NoticeAccountBatchSaveDTOBuilderImpl() {
        }

        protected NoticeAccountBatchSaveDTOBuilderImpl self() {
            return this;
        }

        public NoticeAccountBatchSaveDTO build() {
            return new NoticeAccountBatchSaveDTO(this);
        }
    }

    public static abstract class NoticeAccountBatchSaveDTOBuilder<C extends NoticeAccountBatchSaveDTO, B extends NoticeAccountBatchSaveDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;
        private List<Long> noticeIdList;

        public B accountId(Long accountId) {
            this.accountId = accountId;
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

