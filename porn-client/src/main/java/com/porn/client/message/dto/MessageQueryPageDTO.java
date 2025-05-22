
package com.porn.client.message.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;





 public class MessageQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("账户id")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String accountName;

    @ApiModelProperty("账户名称(模糊)")
     private String lkAccountName;


    /* 15 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MessageQueryPageDTO;
    }



    /* 16 */
    protected MessageQueryPageDTO(MessageQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.accountName = b.accountName;
        this.lkAccountName = b.lkAccountName;
    }

    public static MessageQueryPageDTOBuilder<?, ?> builder() {
        return new MessageQueryPageDTOBuilderImpl();
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

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private String accountName;
        private String lkAccountName;

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

    public MessageQueryPageDTO(Long accountId, String accountName, String lkAccountName) {
        /* 17 */
        this.accountId = accountId;
        this.accountName = accountName;
        this.lkAccountName = lkAccountName;

    }


    public MessageQueryPageDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public String getAccountName() {
        /* 25 */
        return this.accountName;

    }


    public String getLkAccountName() {
        /* 28 */
        return this.lkAccountName;

    }

}


