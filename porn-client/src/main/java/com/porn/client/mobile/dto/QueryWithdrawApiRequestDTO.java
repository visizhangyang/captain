package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class QueryWithdrawApiRequestDTO
        implements Serializable {

    @ApiModelProperty("时间范围, 0-今日, 1-本周, 2-本月, -1全部")
    private Integer dateRange;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private List<Integer> statusList;

    public QueryWithdrawApiRequestDTO(Integer dateRange, List<Integer> statusList) {

        this.dateRange = dateRange;
        this.statusList = statusList;

    }

    public QueryWithdrawApiRequestDTO() {
    }

    public static QueryWithdrawApiRequestDTOBuilder builder() {
        return new QueryWithdrawApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof QueryWithdrawApiRequestDTO;
    }

    public Integer getDateRange() {

        return this.dateRange;

    }

    public void setDateRange(Integer dateRange) {

        this.dateRange = dateRange;
    }

    public List<Integer> getStatusList() {

        return this.statusList;

    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public static class QueryWithdrawApiRequestDTOBuilder {
        private Integer dateRange;
        private List<Integer> statusList;

        public QueryWithdrawApiRequestDTOBuilder dateRange(Integer dateRange) {
            this.dateRange = dateRange;
            return this;
        }

        public QueryWithdrawApiRequestDTOBuilder statusList(List<Integer> statusList) {
            this.statusList = statusList;
            return this;
        }

        public QueryWithdrawApiRequestDTO build() {
            return new QueryWithdrawApiRequestDTO(this.dateRange, this.statusList);
        }

    }

}

