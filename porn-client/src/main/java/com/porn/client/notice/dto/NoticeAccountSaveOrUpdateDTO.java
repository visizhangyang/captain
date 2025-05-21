
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class NoticeAccountSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("公告ID")
     private Long noticeId;



    public void setAccountId(Long accountId) {
        /* 15 */
        this.accountId = accountId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeAccountSaveOrUpdateDTO;
    }



    /* 16 */
    protected NoticeAccountSaveOrUpdateDTO(NoticeAccountSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.noticeId = b.noticeId;
    }

    public static NoticeAccountSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new NoticeAccountSaveOrUpdateDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private Long noticeId;

        public B noticeId(Long noticeId) {
            this.noticeId = noticeId;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NoticeAccountSaveOrUpdateDTO(Long accountId, Long noticeId) {
        /* 17 */
        this.accountId = accountId;
        this.noticeId = noticeId;

    }


    public NoticeAccountSaveOrUpdateDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public Long getNoticeId() {
        /* 25 */
        return this.noticeId;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/dto/NoticeAccountSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */