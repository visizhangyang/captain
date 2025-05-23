package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class CreateRechargeApiRequestDTO
        implements Serializable {

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("充值金额")
    private BigDecimal amount;

    public CreateRechargeApiRequestDTO(String walletCode, BigDecimal amount) {

        this.walletCode = walletCode;
        this.amount = amount;

    }

    public CreateRechargeApiRequestDTO() {
    }

    public static CreateRechargeApiRequestDTOBuilder builder() {
        return new CreateRechargeApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof CreateRechargeApiRequestDTO;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {

        this.walletCode = walletCode;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static class CreateRechargeApiRequestDTOBuilder {
        private String walletCode;
        private BigDecimal amount;

        public CreateRechargeApiRequestDTOBuilder walletCode(String walletCode) {
            this.walletCode = walletCode;
            return this;
        }

        public CreateRechargeApiRequestDTOBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public CreateRechargeApiRequestDTO build() {
            return new CreateRechargeApiRequestDTO(this.walletCode, this.amount);
        }

    }

}

