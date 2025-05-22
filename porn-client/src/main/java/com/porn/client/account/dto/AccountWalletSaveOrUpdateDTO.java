
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;





 public class AccountWalletSaveOrUpdateDTO extends BaseDTO {

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
        return other instanceof AccountWalletSaveOrUpdateDTO;
    }



    /* 16 */
    protected AccountWalletSaveOrUpdateDTO(AccountWalletSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.address = b.address;
    }

    public static AccountWalletSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AccountWalletSaveOrUpdateDTOBuilderImpl();
    }

    private static final class AccountWalletSaveOrUpdateDTOBuilderImpl extends AccountWalletSaveOrUpdateDTOBuilder<AccountWalletSaveOrUpdateDTO, AccountWalletSaveOrUpdateDTOBuilderImpl> {
        private AccountWalletSaveOrUpdateDTOBuilderImpl() {
        }

        protected AccountWalletSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public AccountWalletSaveOrUpdateDTO build() {
            return new AccountWalletSaveOrUpdateDTO(this);
        }
    }

    public static abstract class AccountWalletSaveOrUpdateDTOBuilder<C extends AccountWalletSaveOrUpdateDTO, B extends AccountWalletSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
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

    public AccountWalletSaveOrUpdateDTO(Long accountId, String walletCode, String address) {
        /* 17 */
        this.accountId = accountId;
        this.walletCode = walletCode;
        this.address = address;

    }


    public AccountWalletSaveOrUpdateDTO() {
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


