package com.porn.client.account.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

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

    protected AccountWalletQueryPageDTO(AccountWalletQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.accountId = b.accountId;
        this.walletCode = b.walletCode;
        this.address = b.address;
        this.lkAddress = b.lkAddress;
        this.delFlag = b.delFlag;
    }

    public AccountWalletQueryPageDTO(Long accountId, String walletCode, String address, String lkAddress, Integer delFlag) {

        this.accountId = accountId;
        this.walletCode = walletCode;
        this.address = address;
        this.lkAddress = lkAddress;
        this.delFlag = delFlag;

    }

    public AccountWalletQueryPageDTO() {
    }

    public static AccountWalletQueryPageDTOBuilder<?, ?> builder() {
        return new AccountWalletQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AccountWalletQueryPageDTO;
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

    public String getLkAddress() {

        return this.lkAddress;

    }

    public void setLkAddress(String lkAddress) {
        this.lkAddress = lkAddress;
    }

    public Integer getDelFlag() {

        return this.delFlag;

    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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
        private String address;
        private String lkAddress;
        private Integer delFlag;

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

}

