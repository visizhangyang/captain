
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;
import java.util.List;





 public class QueryWithdrawApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("时间范围, 0-今日, 1-本周, 2-本月, -1全部")
     private Integer dateRange;

    @ApiModelProperty("状态, WithdrawStatusEnum")
     private List<Integer> statusList;



    public void setDateRange(Integer dateRange) {
        /* 16 */
        this.dateRange = dateRange;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof QueryWithdrawApiRequestDTO;
    }



    /* 17 */
    public static QueryWithdrawApiRequestDTOBuilder builder() {
        return new QueryWithdrawApiRequestDTOBuilder();
    }

    public static class QueryWithdrawApiRequestDTOBuilder {
        private Integer dateRange;

        public QueryWithdrawApiRequestDTOBuilder dateRange(Integer dateRange) {
            this.dateRange = dateRange;
            return this;
        }

        private List<Integer> statusList;

        public QueryWithdrawApiRequestDTOBuilder statusList(List<Integer> statusList) {
            this.statusList = statusList;
            return this;
        }

        public QueryWithdrawApiRequestDTO build() {
            return new QueryWithdrawApiRequestDTO(this.dateRange, this.statusList);
        }

    }

    public QueryWithdrawApiRequestDTO(Integer dateRange, List<Integer> statusList) {
        /* 18 */
        this.dateRange = dateRange;
        this.statusList = statusList;

    }


    public QueryWithdrawApiRequestDTO() {
    }



    public Integer getDateRange() {
        /* 23 */
        return this.dateRange;

    }


    public List<Integer> getStatusList() {
        /* 26 */
        return this.statusList;

    }

}


