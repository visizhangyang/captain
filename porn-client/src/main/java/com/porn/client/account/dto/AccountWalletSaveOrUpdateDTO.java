package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountWalletSaveOrUpdateDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("地址")
    private String address;

    protected AccountWalletSaveOrUpdateDTO(AccountWalletSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.address = b.address;
    }

    public AccountWalletSaveOrUpdateDTO(Long accountId, String walletCode, String address) {

        this.accountId = accountId;
        this.walletCode = walletCode;
        this.address = address;

    }

    public AccountWalletSaveOrUpdateDTO() {
    }

    public static AccountWalletSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new AccountWalletSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletSaveOrUpdateDTO;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getAddress() {

        return this.address;

    }

    public void setAddress(String address) {
        this.address = address;
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
        private String walletCode;
        private String address;

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

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

}

