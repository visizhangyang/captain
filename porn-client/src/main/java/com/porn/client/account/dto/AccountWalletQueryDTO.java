package com.porn.client.account.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class AccountWalletQueryDTO extends BaseDTO {

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("地址")
    private String address;

    protected AccountWalletQueryDTO(AccountWalletQueryDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.address = b.address;
    }

    public AccountWalletQueryDTO(Long accountId, String walletCode, String address) {

        this.accountId = accountId;
        this.walletCode = walletCode;
        this.address = address;

    }

    public AccountWalletQueryDTO() {
    }

    public static AccountWalletQueryDTOBuilder<?, ?> builder() {
        return new AccountWalletQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletQueryDTO;
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

