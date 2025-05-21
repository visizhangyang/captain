
package com.porn.client.recharge.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;

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


    /* 17 */
    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    public void setLkWalletRemark(String lkWalletRemark) {
        this.lkWalletRemark = lkWalletRemark;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RechargeQueryPageDTO;
    }



    /* 18 */
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

    public static RechargeQueryPageDTOBuilder<?, ?> builder() {
        return new RechargeQueryPageDTOBuilderImpl();
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

        public B rechargeNo(String rechargeNo) {
            this.rechargeNo = rechargeNo;
            return self();
        }

        private List<Long> accountIdList;
        private String lkAccountName;
        private String lkRemark;
        private String lkWalletRemark;

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

    public RechargeQueryPageDTO(String rechargeNo, String walletCode, Integer status, Long accountId, List<Long> accountIdList, String lkAccountName, String lkRemark, String lkWalletRemark) {
        /* 19 */
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



    public String getRechargeNo() {
        /* 24 */
        return this.rechargeNo;

    }


    public String getWalletCode() {
        /* 27 */
        return this.walletCode;

    }


    public Integer getStatus() {
        /* 30 */
        return this.status;

    }


    public Long getAccountId() {
        /* 33 */
        return this.accountId;

    }


    public List<Long> getAccountIdList() {
        /* 36 */
        return this.accountIdList;

    }


    public String getLkAccountName() {
        /* 39 */
        return this.lkAccountName;

    }


    public String getLkRemark() {
        /* 42 */
        return this.lkRemark;

    }


    public String getLkWalletRemark() {
        /* 45 */
        return this.lkWalletRemark;

    }
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/dto/RechargeQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */