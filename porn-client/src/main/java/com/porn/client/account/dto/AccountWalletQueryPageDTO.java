
package com.porn.client.account.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;


 public class AccountWalletQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("钱包编码")
     private String walletCode;

    @ApiModelProperty("地址")
     private String address;

    @ApiModelProperty("搜索地址")
     private String lkAddress;

    @ApiModelProperty("是否删除")
     private Integer delFlag;


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

    public void setLkAddress(String lkAddress) {
        this.lkAddress = lkAddress;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }


    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletQueryPageDTO;
    }



    /* 16 */
    protected AccountWalletQueryPageDTO(AccountWalletQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.address = b.address;
        this.lkAddress = b.lkAddress;
        this.delFlag = b.delFlag;
    }

    public static AccountWalletQueryPageDTOBuilder<?, ?> builder() {
        return new AccountWalletQueryPageDTOBuilderImpl();
    }

    private static final class AccountWalletQueryPageDTOBuilderImpl extends AccountWalletQueryPageDTOBuilder<AccountWalletQueryPageDTO, AccountWalletQueryPageDTOBuilderImpl> {
        private AccountWalletQueryPageDTOBuilderImpl() {
        }

        protected AccountWalletQueryPageDTOBuilderImpl self() {
            return this;
        }

        public AccountWalletQueryPageDTO build() {
            return new AccountWalletQueryPageDTO(this);
        }
    }

    public static abstract class AccountWalletQueryPageDTOBuilder<C extends AccountWalletQueryPageDTO, B extends AccountWalletQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long accountId;
        private String walletCode;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        private String address;
        private String lkAddress;
        private Integer delFlag;

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B address(String address) {
            this.address = address;
            return self();
        }

        public B lkAddress(String lkAddress) {
            this.lkAddress = lkAddress;
            return self();
        }

        public B delFlag(Integer delFlag) {
            this.delFlag = delFlag;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public AccountWalletQueryPageDTO(Long accountId, String walletCode, String address, String lkAddress, Integer delFlag) {
        /* 17 */
        this.accountId = accountId;
        this.walletCode = walletCode;
        this.address = address;
        this.lkAddress = lkAddress;
        this.delFlag = delFlag;

    }


    public AccountWalletQueryPageDTO() {
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


    public String getLkAddress() {
        /* 31 */
        return this.lkAddress;

    }



    public Integer getDelFlag() {
        /* 35 */
        return this.delFlag;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/account/dto/AccountWalletQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */