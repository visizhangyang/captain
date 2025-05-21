
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class NoticeDeleteDTO
         extends BaseDTO
         {

    @ApiModelProperty("ID列表")
     private List<Long> idList;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeDeleteDTO;
    }



    /* 17 */
    protected NoticeDeleteDTO(NoticeDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public static NoticeDeleteDTOBuilder<?, ?> builder() {
        return new NoticeDeleteDTOBuilderImpl();
    }

    private static final class NoticeDeleteDTOBuilderImpl extends NoticeDeleteDTOBuilder<NoticeDeleteDTO, NoticeDeleteDTOBuilderImpl> {
        private NoticeDeleteDTOBuilderImpl() {
        }

        protected NoticeDeleteDTOBuilderImpl self() {
            return this;
        }

        public NoticeDeleteDTO build() {
            return new NoticeDeleteDTO(this);
        }
    }

    public static abstract class NoticeDeleteDTOBuilder<C extends NoticeDeleteDTO, B extends NoticeDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private List<Long> idList;

        protected abstract B self();

        public abstract C build();


    }

    public NoticeDeleteDTO() {
    }

    public NoticeDeleteDTO(List<Long> idList) {
        /* 19 */
        this.idList = idList;

    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/notice/dto/NoticeDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */