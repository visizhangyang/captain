
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BasePageDTO;

import java.io.Serializable;
import java.util.List;


 public class QueryOrderApiRequestDTO extends BasePageDTO implements Serializable {

    @ApiModelProperty("商户id")
     private Long merchantId;

    @ApiModelProperty("订单状态, OrderStatusEnum")
     private Integer orderStatus;

    @ApiModelProperty("订单状态, OrderStatusEnum")
     private List<Integer> orderStatusList;

    @ApiModelProperty("时间范围, 0-今日, 1-本周, 2-本月, -1全部")
     private Integer dateRange;

    @ApiModelProperty("是否查询所有的账户")
     private Boolean queryAllAccount;


    /* 17 */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderStatusList(List<Integer> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }

    public void setDateRange(Integer dateRange) {
        this.dateRange = dateRange;
    }

    public void setQueryAllAccount(Boolean queryAllAccount) {
        this.queryAllAccount = queryAllAccount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof QueryOrderApiRequestDTO;
    }



    /* 18 */
    protected QueryOrderApiRequestDTO(QueryOrderApiRequestDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.orderStatus = b.orderStatus;
        this.orderStatusList = b.orderStatusList;
        this.dateRange = b.dateRange;
        this.queryAllAccount = b.queryAllAccount;
    }

    public static QueryOrderApiRequestDTOBuilder<?, ?> builder() {
        return new QueryOrderApiRequestDTOBuilderImpl();
    }

    private static final class QueryOrderApiRequestDTOBuilderImpl extends QueryOrderApiRequestDTOBuilder<QueryOrderApiRequestDTO, QueryOrderApiRequestDTOBuilderImpl> {
        private QueryOrderApiRequestDTOBuilderImpl() {
        }

        protected QueryOrderApiRequestDTOBuilderImpl self() {
            return this;
        }

        public QueryOrderApiRequestDTO build() {
            return new QueryOrderApiRequestDTO(this);
        }
    }

    public static abstract class QueryOrderApiRequestDTOBuilder<C extends QueryOrderApiRequestDTO, B extends QueryOrderApiRequestDTOBuilder<C, B>> extends BasePageDTO.BasePageDTOBuilder<C, B> {
        private Long merchantId;
        private Integer orderStatus;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private List<Integer> orderStatusList;
        private Integer dateRange;
        private Boolean queryAllAccount;

        public B orderStatus(Integer orderStatus) {
            this.orderStatus = orderStatus;
            return self();
        }

        public B orderStatusList(List<Integer> orderStatusList) {
            this.orderStatusList = orderStatusList;
            return self();
        }

        public B dateRange(Integer dateRange) {
            this.dateRange = dateRange;
            return self();
        }

        public B queryAllAccount(Boolean queryAllAccount) {
            this.queryAllAccount = queryAllAccount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public QueryOrderApiRequestDTO(Long merchantId, Integer orderStatus, List<Integer> orderStatusList, Integer dateRange, Boolean queryAllAccount) {
        /* 19 */
        this.merchantId = merchantId;
        this.orderStatus = orderStatus;
        this.orderStatusList = orderStatusList;
        this.dateRange = dateRange;
        this.queryAllAccount = queryAllAccount;

    }



    public QueryOrderApiRequestDTO() {
    }



    public Long getMerchantId() {
        /* 25 */
        return this.merchantId;

    }


    public Integer getOrderStatus() {
        /* 28 */
        return this.orderStatus;

    }


    public List<Integer> getOrderStatusList() {
        /* 31 */
        return this.orderStatusList;

    }


    public Integer getDateRange() {
        /* 34 */
        return this.dateRange;

    }


    public Boolean getQueryAllAccount() {
        /* 37 */
        return this.queryAllAccount;

    }

}


