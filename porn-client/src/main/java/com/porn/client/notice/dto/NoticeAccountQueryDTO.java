
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;

import java.util.List;






 public class NoticeAccountQueryDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("公告ID")
     private Long noticeId;

    @ApiModelProperty("公告ID列表")
     private List<Long> noticeIdList;


    /* 16 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public void setNoticeIdList(List<Long> noticeIdList) {
        this.noticeIdList = noticeIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeAccountQueryDTO;
    }



    /* 17 */
    protected NoticeAccountQueryDTO(NoticeAccountQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.noticeId = b.noticeId;
        this.noticeIdList = b.noticeIdList;
    }

    public static NoticeAccountQueryDTOBuilder<?, ?> builder() {
        return new NoticeAccountQueryDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Long noticeId;
        private List<Long> noticeIdList;

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

    public NoticeAccountQueryDTO(Long accountId, Long noticeId, List<Long> noticeIdList) {
        /* 18 */
        this.accountId = accountId;
        this.noticeId = noticeId;
        this.noticeIdList = noticeIdList;

    }


    public NoticeAccountQueryDTO() {
    }



    public Long getAccountId() {
        /* 23 */
        return this.accountId;

    }


    public Long getNoticeId() {
        /* 26 */
        return this.noticeId;

    }


    public List<Long> getNoticeIdList() {
        /* 29 */
        return this.noticeIdList;

    }

}


