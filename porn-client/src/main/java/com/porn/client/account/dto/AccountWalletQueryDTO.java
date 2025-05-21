
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountWalletQueryDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("钱包编码")
     private String walletCode;

    @ApiModelProperty("地址")
     private String address;


    /* 15 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletQueryDTO;
    }



    /* 16 */
    protected AccountWalletQueryDTO(AccountWalletQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.address = b.address;
    }

    public static AccountWalletQueryDTOBuilder<?, ?> builder() {
        return new AccountWalletQueryDTOBuilderImpl();
    }

    private static final class AccountWalletQueryDTOBuilderImpl extends AccountWalletQueryDTOBuilder<AccountWalletQueryDTO, AccountWalletQueryDTOBuilderImpl> {
        private AccountWalletQueryDTOBuilderImpl() {
        }

        protected AccountWalletQueryDTOBuilderImpl self() {
            return this;
        }

        public AccountWalletQueryDTO build() {
            return new AccountWalletQueryDTO(this);
        }
    }

    public static abstract class AccountWalletQueryDTOBuilder<C extends AccountWalletQueryDTO, B extends AccountWalletQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long accountId;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private String walletCode;
        private String address;

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B address(String address) {
            this.address = address;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountWalletQueryDTO(Long accountId, String walletCode, String address) {
        /* 17 */
        this.accountId = accountId;
        this.walletCode = walletCode;
        this.address = address;

    }


    public AccountWalletQueryDTO() {
    }



    public Long getAccountId() {
        /* 22 */
        return this.accountId;

    }


    public String getWalletCode() {
        /* 25 */
        return this.walletCode;

    }


    public String getAddress() {
        /* 28 */
        return this.address;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountWalletQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */