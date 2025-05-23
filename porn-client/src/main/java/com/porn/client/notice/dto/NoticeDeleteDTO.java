package com.porn.client.notice.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class NoticeDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("ID列表")
    private List<Long> idList;

    protected NoticeDeleteDTO(NoticeDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public NoticeDeleteDTO() {
    }

    public NoticeDeleteDTO(List<Long> idList) {

        this.idList = idList;

    }

    public static NoticeDeleteDTOBuilder<?, ?> builder() {
        return new NoticeDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof NoticeDeleteDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
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
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

