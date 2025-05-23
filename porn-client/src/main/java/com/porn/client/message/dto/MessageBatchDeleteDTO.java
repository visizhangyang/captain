package com.porn.client.message.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class MessageBatchDeleteDTO
        extends BaseDTO {

    @ApiModelProperty("批量删除")
    private List<Long> idList;

    protected MessageBatchDeleteDTO(MessageBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public MessageBatchDeleteDTO(List<Long> idList) {

        this.idList = idList;

    }

    public MessageBatchDeleteDTO() {
    }

    public static MessageBatchDeleteDTOBuilder<?, ?> builder() {
        return new MessageBatchDeleteDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MessageBatchDeleteDTO;
    }

    public List<Long> getIdList() {

        return this.idList;

    }

    public void setIdList(List<Long> idList) {

        this.idList = idList;
    }

    private static final class MessageBatchDeleteDTOBuilderImpl extends MessageBatchDeleteDTOBuilder<MessageBatchDeleteDTO, MessageBatchDeleteDTOBuilderImpl> {
        private MessageBatchDeleteDTOBuilderImpl() {
        }

        protected MessageBatchDeleteDTOBuilderImpl self() {
            return this;
        }

        public MessageBatchDeleteDTO build() {
            return new MessageBatchDeleteDTO(this);
        }
    }

    public static abstract class MessageBatchDeleteDTOBuilder<C extends MessageBatchDeleteDTO, B extends MessageBatchDeleteDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private List<Long> idList;

        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

