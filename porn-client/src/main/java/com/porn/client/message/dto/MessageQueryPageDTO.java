package com.porn.client.message.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class MessageQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("账户名称(模糊)")
    private String lkAccountName;

    protected MessageQueryPageDTO(MessageQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.lkAccountName = b.lkAccountName;
    }

    public MessageQueryPageDTO(Long accountId, String accountName, String lkAccountName) {

        this.accountId = accountId;
        this.accountName = accountName;
        this.lkAccountName = lkAccountName;

    }

    public MessageQueryPageDTO() {
    }

    public static MessageQueryPageDTOBuilder<?, ?> builder() {
        return new MessageQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MessageQueryPageDTO;
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

    public String getLkAccountName() {

        return this.lkAccountName;

    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    private static final class MessageQueryPageDTOBuilderImpl extends MessageQueryPageDTOBuilder<MessageQueryPageDTO, MessageQueryPageDTOBuilderImpl> {
        private MessageQueryPageDTOBuilderImpl() {
        }

        protected MessageQueryPageDTOBuilderImpl self() {
            return this;
        }

        public MessageQueryPageDTO build() {
            return new MessageQueryPageDTO(this);
        }
    }

    public static abstract class MessageQueryPageDTOBuilder<C extends MessageQueryPageDTO, B extends MessageQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long accountId;
        private String accountName;
        private String lkAccountName;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        public B lkAccountName(String lkAccountName) {
            this.lkAccountName = lkAccountName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

