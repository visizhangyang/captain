package com.porn.client.recharge.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RechargeQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("充值流水号")
    private String rechargeNo;
    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("状态, RechargeStatusEnum")
    private Integer status;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("备注")
    private String lkRemark;

    @ApiModelProperty("地址备注")
    private String lkWalletRemark;

    protected RechargeQueryPageDTO(RechargeQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.rechargeNo = b.rechargeNo;
        this.walletCode = b.walletCode;
        this.status = b.status;
        this.accountId = b.accountId;
        this.accountIdList = b.accountIdList;
        this.lkAccountName = b.lkAccountName;
        this.lkRemark = b.lkRemark;
        this.lkWalletRemark = b.lkWalletRemark;
    }

    public RechargeQueryPageDTO(String rechargeNo, String walletCode, Integer status, Long accountId, List<Long> accountIdList, String lkAccountName, String lkRemark, String lkWalletRemark) {

        this.rechargeNo = rechargeNo;
        this.walletCode = walletCode;
        this.status = status;
        this.accountId = accountId;
        this.accountIdList = accountIdList;
        this.lkAccountName = lkAccountName;
        this.lkRemark = lkRemark;
        this.lkWalletRemark = lkWalletRemark;

    }

    public RechargeQueryPageDTO() {
    }

    public static RechargeQueryPageDTOBuilder<?, ?> builder() {
        return new RechargeQueryPageDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RechargeQueryPageDTO;
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

    public List<Long> getAccountIdList() {

        return this.accountIdList;

    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
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

    private static final class RechargeQueryPageDTOBuilderImpl extends RechargeQueryPageDTOBuilder<RechargeQueryPageDTO, RechargeQueryPageDTOBuilderImpl> {
        private RechargeQueryPageDTOBuilderImpl() {
        }

        protected RechargeQueryPageDTOBuilderImpl self() {
            return this;
        }

        public RechargeQueryPageDTO build() {
            return new RechargeQueryPageDTO(this);
        }
    }

    public static abstract class RechargeQueryPageDTOBuilder<C extends RechargeQueryPageDTO, B extends RechargeQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private String rechargeNo;
        private String walletCode;
        private Integer status;
        private Long accountId;
        private List<Long> accountIdList;
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

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
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

