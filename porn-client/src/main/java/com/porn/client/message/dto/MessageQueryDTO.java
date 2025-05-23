package com.porn.client.message.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class MessageQueryDTO extends BaseDTO {

    @ApiModelProperty("消息体")
    private String msg;

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    protected MessageQueryDTO(MessageQueryDTOBuilder<?, ?> b) {
        super(b);
        this.msg = b.msg;
        this.accountId = b.accountId;
        this.accountName = b.accountName;
    }

    public MessageQueryDTO(String msg, Long accountId, String accountName) {

        this.msg = msg;
        this.accountId = accountId;
        this.accountName = accountName;

    }

    public MessageQueryDTO() {
    }

    public static MessageQueryDTOBuilder<?, ?> builder() {
        return new MessageQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MessageQueryDTO;
    }

    public String getMsg() {

        return this.msg;

    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {

        return this.accountName;

    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
        private Long accountId;
        private String accountName;

        public B msg(String msg) {
            this.msg = msg;
            return self();
        }

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

}

