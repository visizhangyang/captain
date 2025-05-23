package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class LotteryApiRequestDTO
        implements Serializable {

    @ApiModelProperty("幸运序号")
    private Integer luckIndex;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    public LotteryApiRequestDTO(Integer luckIndex, BigDecimal amount) {

        this.luckIndex = luckIndex;
        this.amount = amount;

    }

    public LotteryApiRequestDTO() {
    }

    public static LotteryApiRequestDTOBuilder builder() {
        return new LotteryApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof LotteryApiRequestDTO;
    }

    public Integer getLuckIndex() {

        return this.luckIndex;

    }

    public void setLuckIndex(Integer luckIndex) {

        this.luckIndex = luckIndex;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static class LotteryApiRequestDTOBuilder {
        private Integer luckIndex;
        private BigDecimal amount;

        public LotteryApiRequestDTOBuilder luckIndex(Integer luckIndex) {
            this.luckIndex = luckIndex;
            return this;
        }

        public LotteryApiRequestDTOBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public LotteryApiRequestDTO build() {
            return new LotteryApiRequestDTO(this.luckIndex, this.amount);
        }

    }

}

