
package com.porn.client.notice.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.time.LocalDateTime;








 public class NoticeUpdateCreateTimeDTO
         extends BaseDTO
         {

    @ApiModelProperty("更新时间")
     private LocalDateTime createTime;



    public void setCreateTime(LocalDateTime createTime) {
        /* 17 */
        this.createTime = createTime;
    }


    protected boolean canEqual(Object other) {
        return other instanceof NoticeUpdateCreateTimeDTO;
    }



    /* 18 */
    protected NoticeUpdateCreateTimeDTO(NoticeUpdateCreateTimeDTOBuilder<?, ?> b) {
        super(b);
        this.createTime = b.createTime;
    }

    public static NoticeUpdateCreateTimeDTOBuilder<?, ?> builder() {
        return new NoticeUpdateCreateTimeDTOBuilderImpl();
    }

    private static final class NoticeUpdateCreateTimeDTOBuilderImpl extends NoticeUpdateCreateTimeDTOBuilder<NoticeUpdateCreateTimeDTO, NoticeUpdateCreateTimeDTOBuilderImpl> {
        private NoticeUpdateCreateTimeDTOBuilderImpl() {
        }

        protected NoticeUpdateCreateTimeDTOBuilderImpl self() {
            return this;
        }

        public NoticeUpdateCreateTimeDTO build() {
            return new NoticeUpdateCreateTimeDTO(this);
        }
    }

    public static abstract class NoticeUpdateCreateTimeDTOBuilder<C extends NoticeUpdateCreateTimeDTO, B extends NoticeUpdateCreateTimeDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return self();
        }

        private LocalDateTime createTime;

        protected abstract B self();

        public abstract C build();


    }

    public NoticeUpdateCreateTimeDTO() {
    }

    public NoticeUpdateCreateTimeDTO(LocalDateTime createTime) {
        /* 20 */
        this.createTime = createTime;

    }



    public LocalDateTime getCreateTime() {
        /* 24 */
        return this.createTime;

    }

}


