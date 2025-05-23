package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class NoticeUpdateCreateTimeDTO
        extends BaseDTO {

    @ApiModelProperty("更新时间")
    private LocalDateTime createTime;

    protected NoticeUpdateCreateTimeDTO(NoticeUpdateCreateTimeDTOBuilder<?, ?> b) {
        super(b);
        this.createTime = b.createTime;
    }

    public NoticeUpdateCreateTimeDTO() {
    }

    public NoticeUpdateCreateTimeDTO(LocalDateTime createTime) {

        this.createTime = createTime;

    }

    public static NoticeUpdateCreateTimeDTOBuilder<?, ?> builder() {
        return new NoticeUpdateCreateTimeDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeUpdateCreateTimeDTO;
    }

    public LocalDateTime getCreateTime() {

        return this.createTime;

    }

    public void setCreateTime(LocalDateTime createTime) {

        this.createTime = createTime;
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
        private LocalDateTime createTime;

        public B createTime(LocalDateTime createTime) {
            this.createTime = createTime;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

