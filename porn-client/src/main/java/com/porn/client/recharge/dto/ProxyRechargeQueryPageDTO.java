package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

public class ProxyRechargeQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("充值流水号")
    private String rechargeNo;
    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("状态, RechargeStatusEnum")
    private Integer status;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("备注")
    private String lkRemark;

    @ApiModelProperty("地址备注")
    private String lkWalletRemark;

    protected ProxyRechargeQueryPageDTO(ProxyRechargeQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.rechargeNo = b.rechargeNo;
        this.walletCode = b.walletCode;
        this.status = b.status;
        this.accountId = b.accountId;
        this.lkAccountName = b.lkAccountName;
        this.lkRemark = b.lkRemark;
        this.lkWalletRemark = b.lkWalletRemark;
    }

    public ProxyRechargeQueryPageDTO(String rechargeNo, String walletCode, Integer status, Long accountId, String lkAccountName, String lkRemark, String lkWalletRemark) {

        this.rechargeNo = rechargeNo;
        this.walletCode = walletCode;
        this.status = status;
        this.accountId = accountId;
        this.lkAccountName = lkAccountName;
        this.lkRemark = lkRemark;
        this.lkWalletRemark = lkWalletRemark;

    }

    public ProxyRechargeQueryPageDTO() {
    }

    public static ProxyRechargeQueryPageDTOBuilder<?, ?> builder() {
        return new ProxyRechargeQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ProxyRechargeQueryPageDTO;
    }

    public String getRechargeNo() {

        return this.rechargeNo;

    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLkAccountName() {

        return this.lkAccountName;

    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public String getLkRemark() {

        return this.lkRemark;

    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    public String getLkWalletRemark() {

        return this.lkWalletRemark;

    }

    public void setLkWalletRemark(String lkWalletRemark) {
        this.lkWalletRemark = lkWalletRemark;
    }

    private static final class ProxyRechargeQueryPageDTOBuilderImpl extends ProxyRechargeQueryPageDTOBuilder<ProxyRechargeQueryPageDTO, ProxyRechargeQueryPageDTOBuilderImpl> {
        private ProxyRechargeQueryPageDTOBuilderImpl() {
        }

        protected ProxyRechargeQueryPageDTOBuilderImpl self() {
            return this;
        }

        public ProxyRechargeQueryPageDTO build() {
            return new ProxyRechargeQueryPageDTO(this);
        }
    }

    public static abstract class ProxyRechargeQueryPageDTOBuilder<C extends ProxyRechargeQueryPageDTO, B extends ProxyRechargeQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String rechargeNo;
        private String walletCode;
        private Integer status;
        private Long accountId;
        private String lkAccountName;
        private String lkRemark;
        private String lkWalletRemark;

        public B rechargeNo(String rechargeNo) {
            this.rechargeNo = rechargeNo;
            return self();
        }

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B lkAccountName(String lkAccountName) {
            this.lkAccountName = lkAccountName;
            return self();
        }

        public B lkRemark(String lkRemark) {
            this.lkRemark = lkRemark;
            return self();
        }

        public B lkWalletRemark(String lkWalletRemark) {
            this.lkWalletRemark = lkWalletRemark;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

