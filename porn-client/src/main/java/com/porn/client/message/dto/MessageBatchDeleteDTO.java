
package com.porn.client.message.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


import java.util.List;







 public class MessageBatchDeleteDTO
         extends BaseDTO
         {

    @ApiModelProperty("批量删除")
     private List<Long> idList;



    public void setIdList(List<Long> idList) {
        /* 16 */
        this.idList = idList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MessageBatchDeleteDTO;
    }



    /* 17 */
    protected MessageBatchDeleteDTO(MessageBatchDeleteDTOBuilder<?, ?> b) {
        super(b);
        this.idList = b.idList;
    }

    public static MessageBatchDeleteDTOBuilder<?, ?> builder() {
        return new MessageBatchDeleteDTOBuilderImpl();
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
        public B idList(List<Long> idList) {
            this.idList = idList;
            return self();
        }

        private List<Long> idList;

        protected abstract B self();

        public abstract C build();

    }

    public MessageBatchDeleteDTO(List<Long> idList) {
        /* 18 */
        this.idList = idList;

    }


    public MessageBatchDeleteDTO() {
    }



    public List<Long> getIdList() {
        /* 23 */
        return this.idList;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/message/dto/MessageBatchDeleteDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */