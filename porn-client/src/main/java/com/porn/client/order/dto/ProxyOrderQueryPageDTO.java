
package com.porn.client.order.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;

import java.time.LocalDateTime;
import java.util.List;



 public class ProxyOrderQueryPageDTO extends BasePageDTO {

    @ApiModelProperty("商户id")
     private Long merchantId;

    @ApiModelProperty("状态")
     private Integer orderStatus;

    @ApiModelProperty("OrderTypeEnum, 0-真人, 1-机器人")
     private Integer orderType;

    @ApiModelProperty("账户id")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String lkAccountName;

    @ApiModelProperty("备注")
     private String lkRemark;


    /* 18 */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;
    @ApiModelProperty("商户ID列表")
    private List<Long> merchantIdList;
    @ApiModelProperty("起始时间")
    private LocalDateTime startTime;
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;
    @ApiModelProperty("订单状态, OrderStatusEnum")
    private List<Integer> orderStatusList;
    @ApiModelProperty("收款地址钱包编码")
    private String walletCode;

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setLkAccountName(String lkAccountName) {
        this.lkAccountName = lkAccountName;
    }

    public void setLkRemark(String lkRemark) {
        this.lkRemark = lkRemark;
    }

    public void setAccountIdList(List<Long> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public void setMerchantIdList(List<Long> merchantIdList) {
        this.merchantIdList = merchantIdList;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setOrderStatusList(List<Integer> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ProxyOrderQueryPageDTO;
    }



    /* 19 */
    protected ProxyOrderQueryPageDTO(ProxyOrderQueryPageDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.orderStatus = b.orderStatus;
        this.orderType = b.orderType;
        this.accountId = b.accountId;
        this.lkAccountName = b.lkAccountName;
        this.lkRemark = b.lkRemark;
        this.accountIdList = b.accountIdList;
        this.merchantIdList = b.merchantIdList;
        this.startTime = b.startTime;
        this.endTime = b.endTime;
        this.orderStatusList = b.orderStatusList;
        this.walletCode = b.walletCode;
    }

    public static ProxyOrderQueryPageDTOBuilder<?, ?> builder() {
        return new ProxyOrderQueryPageDTOBuilderImpl();
    }

    private static final class ProxyOrderQueryPageDTOBuilderImpl extends ProxyOrderQueryPageDTOBuilder<ProxyOrderQueryPageDTO, ProxyOrderQueryPageDTOBuilderImpl> {
        private ProxyOrderQueryPageDTOBuilderImpl() {
        }

        protected ProxyOrderQueryPageDTOBuilderImpl self() {
            return this;
        }

        public ProxyOrderQueryPageDTO build() {
            return new ProxyOrderQueryPageDTO(this);
        }
    }

    public static abstract class ProxyOrderQueryPageDTOBuilder<C extends ProxyOrderQueryPageDTO, B extends ProxyOrderQueryPageDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long merchantId;
        private Integer orderStatus;
        private Integer orderType;
        private Long accountId;
        private String lkAccountName;
        private String lkRemark;
        private List<Long> accountIdList;
        private List<Long> merchantIdList;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private List<Integer> orderStatusList;
        private String walletCode;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B orderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
            return self();
        }

        public B orderType(Integer orderType) {
            this.orderType = orderType;
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

        public B accountIdList(List<Long> accountIdList) {
            this.accountIdList = accountIdList;
            return self();
        }

        public B merchantIdList(List<Long> merchantIdList) {
            this.merchantIdList = merchantIdList;
            return self();
        }

        public B startTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return self();
        }

        public B endTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return self();
        }

        public B orderStatusList(List<Integer> orderStatusList) {
            this.orderStatusList = orderStatusList;
            return self();
        }

        public B walletCode(String walletCode) {
            this.walletCode = walletCode;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public ProxyOrderQueryPageDTO(Long merchantId, Integer orderStatus, Integer orderType, Long accountId, String lkAccountName, String lkRemark, List<Long> accountIdList, List<Long> merchantIdList, LocalDateTime startTime, LocalDateTime endTime, List<Integer> orderStatusList, String walletCode) {
        /* 20 */
        this.merchantId = merchantId;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.accountId = accountId;
        this.lkAccountName = lkAccountName;
        this.lkRemark = lkRemark;
        this.accountIdList = accountIdList;
        this.merchantIdList = merchantIdList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatusList = orderStatusList;
        this.walletCode = walletCode;

    }


    public ProxyOrderQueryPageDTO() {
    }



    public Long getMerchantId() {
        /* 25 */
        return this.merchantId;

    }


    public Integer getOrderStatus() {
        /* 28 */
        return this.orderStatus;

    }


    public Integer getOrderType() {
        /* 31 */
        return this.orderType;

    }


    public Long getAccountId() {
        /* 34 */
        return this.accountId;

    }


    public String getLkAccountName() {
        /* 37 */
        return this.lkAccountName;

    }


    public String getLkRemark() {
        /* 40 */
        return this.lkRemark;

    }


    public List<Long> getAccountIdList() {
        /* 43 */
        return this.accountIdList;

    }


    public List<Long> getMerchantIdList() {
        /* 46 */
        return this.merchantIdList;

    }


    public LocalDateTime getStartTime() {
        /* 49 */
        return this.startTime;

    }


    public LocalDateTime getEndTime() {
        /* 52 */
        return this.endTime;

    }


    public List<Integer> getOrderStatusList() {
        /* 55 */
        return this.orderStatusList;

    }


    public String getWalletCode() {
        /* 58 */
        return this.walletCode;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/order/dto/ProxyOrderQueryPageDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */