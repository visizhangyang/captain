
package com.porn.client.message.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class MessageSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("消息体")
     private String msg;

    @ApiModelProperty("账户id")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String accountName;


    /* 15 */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MessageSaveOrUpdateDTO;
    }



    /* 16 */
    protected MessageSaveOrUpdateDTO(MessageSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.msg = b.msg;
        this.accountId = b.accountId;
        this.accountName = b.accountName;
    }

    public static MessageSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new MessageSaveOrUpdateDTOBuilderImpl();
    }

    private static final class MessageSaveOrUpdateDTOBuilderImpl extends MessageSaveOrUpdateDTOBuilder<MessageSaveOrUpdateDTO, MessageSaveOrUpdateDTOBuilderImpl> {
        private MessageSaveOrUpdateDTOBuilderImpl() {
        }

        protected MessageSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public MessageSaveOrUpdateDTO build() {
            return new MessageSaveOrUpdateDTO(this);
        }
    }

    public static abstract class MessageSaveOrUpdateDTOBuilder<C extends MessageSaveOrUpdateDTO, B extends MessageSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String msg;

        public B msg(String msg) {
            this.msg = msg;
            return self();
        }

        private Long accountId;
        private String accountName;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public MessageSaveOrUpdateDTO(String msg, Long accountId, String accountName) {
        /* 17 */
        this.msg = msg;
        this.accountId = accountId;
        this.accountName = accountName;

    }


    public MessageSaveOrUpdateDTO() {
    }



    public String getMsg() {
        /* 22 */
        return this.msg;

    }


    public Long getAccountId() {
        /* 25 */
        return this.accountId;

    }


    public String getAccountName() {
        /* 28 */
        return this.accountName;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/message/dto/MessageSaveOrUpdateDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */