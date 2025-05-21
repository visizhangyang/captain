
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountOfflineDTO
         extends BaseDTO
         {

    @ApiModelProperty("活跃的token")
     private String activeToken;



    public void setActiveToken(String activeToken) {
        /* 15 */
        this.activeToken = activeToken;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountOfflineDTO;
    }



    /* 16 */
    protected AccountOfflineDTO(AccountOfflineDTOBuilder<?, ?> b) {
        super(b);
        this.activeToken = b.activeToken;
    }

    public static AccountOfflineDTOBuilder<?, ?> builder() {
        return new AccountOfflineDTOBuilderImpl();
    }

    private static final class AccountOfflineDTOBuilderImpl extends AccountOfflineDTOBuilder<AccountOfflineDTO, AccountOfflineDTOBuilderImpl> {
        private AccountOfflineDTOBuilderImpl() {
        }

        protected AccountOfflineDTOBuilderImpl self() {
            return this;
        }

        public AccountOfflineDTO build() {
            return new AccountOfflineDTO(this);
        }
    }

    public static abstract class AccountOfflineDTOBuilder<C extends AccountOfflineDTO, B extends AccountOfflineDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        public B activeToken(String activeToken) {
            this.activeToken = activeToken;
            return self();
        }

        private String activeToken;

        protected abstract B self();

        public abstract C build();

    }

    public AccountOfflineDTO(String activeToken) {
        /* 17 */
        this.activeToken = activeToken;

    }


    public AccountOfflineDTO() {
    }



    public String getActiveToken() {
        /* 22 */
        return this.activeToken;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountOfflineDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */