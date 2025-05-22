
package com.porn.client.message.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class MessageQueryDTO extends BaseDTO {

    @ApiModelProperty("消息体")
     private String msg;

    @ApiModelProperty("账户id")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String accountName;


    /* 16 */
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
        return other instanceof MessageQueryDTO;
    }



    /* 17 */
    protected MessageQueryDTO(MessageQueryDTOBuilder<?, ?> b) {
        super(b);
        this.msg = b.msg;
        this.accountId = b.accountId;
        this.accountName = b.accountName;
    }

    public static MessageQueryDTOBuilder<?, ?> builder() {
        return new MessageQueryDTOBuilderImpl();
    }

    private static final class MessageQueryDTOBuilderImpl extends MessageQueryDTOBuilder<MessageQueryDTO, MessageQueryDTOBuilderImpl> {
        private MessageQueryDTOBuilderImpl() {
        }

        protected MessageQueryDTOBuilderImpl self() {
            return this;
        }

        public MessageQueryDTO build() {
            return new MessageQueryDTO(this);
        }
    }

    public static abstract class MessageQueryDTOBuilder<C extends MessageQueryDTO, B extends MessageQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
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

    public MessageQueryDTO(String msg, Long accountId, String accountName) {
        /* 18 */
        this.msg = msg;
        this.accountId = accountId;
        this.accountName = accountName;

    }


    public MessageQueryDTO() {
    }



    public String getMsg() {
        /* 23 */
        return this.msg;

    }


    public Long getAccountId() {
        /* 26 */
        return this.accountId;

    }


    public String getAccountName() {
        /* 29 */
        return this.accountName;

    }

}


