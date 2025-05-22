
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class NoticeAccountBatchSaveDTO extends BaseDTO {
    
    @ApiModelProperty("账户ID")
     private Long accountId;
    
    @ApiModelProperty("公告ID列表")
     private List<Long> noticeIdList;

    
    
    public void setAccountId(Long accountId) {
        /* 16 */
        this.accountId = accountId;
    }

    public void setNoticeIdList(List<Long> noticeIdList) {
        this.noticeIdList = noticeIdList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeAccountBatchSaveDTO;
    }



    /* 17 */
    protected NoticeAccountBatchSaveDTO(NoticeAccountBatchSaveDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.noticeIdList = b.noticeIdList;
    }

    public static NoticeAccountBatchSaveDTOBuilder<?, ?> builder() {
        return new NoticeAccountBatchSaveDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private List<Long> noticeIdList;

        public B noticeIdList(List<Long> noticeIdList) {
            this.noticeIdList = noticeIdList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public NoticeAccountBatchSaveDTO(Long accountId, List<Long> noticeIdList) {
        /* 18 */
        this.accountId = accountId;
        this.noticeIdList = noticeIdList;
        
    }

    
    public NoticeAccountBatchSaveDTO() {
    }

    
    
    public Long getAccountId() {
        /* 23 */
        return this.accountId;
        
    }

    
    public List<Long> getNoticeIdList() {
        /* 26 */
        return this.noticeIdList;
        
    }
    
}


