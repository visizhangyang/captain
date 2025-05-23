package com.porn.client.recharge.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class TrcTransferItemDTO implements Serializable {

    @ApiModelProperty("金额, 需要去除decimals位后才是金额")
    private BigDecimal amount;

    @ApiModelProperty("源地址")
    private String from;

    @ApiModelProperty("接收地址, 也就是我们钱包地址")
    private String to;

    @ApiModelProperty("交易hash")
    private String hash;

    @ApiModelProperty("合约返回值")
    private String contract_ret;

    @ApiModelProperty("精度")
    private Integer decimals;

    protected TrcTransferItemDTO(TrcTransferItemDTOBuilder<?, ?> b) {
        this.amount = b.amount;
        this.from = b.from;
        this.to = b.to;
        this.hash = b.hash;
        this.contract_ret = b.contract_ret;
        this.decimals = b.decimals;
    }

    public TrcTransferItemDTO(BigDecimal amount, String from, String to, String hash, String contract_ret, Integer decimals) {

        this.amount = amount;
        this.from = from;
        this.to = to;
        this.hash = hash;
        this.contract_ret = contract_ret;
        this.decimals = decimals;

    }

    public TrcTransferItemDTO() {
    }

    public static TrcTransferItemDTOBuilder<?, ?> builder() {
        return new TrcTransferItemDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof TrcTransferItemDTO;
    }

    public BigDecimal getAmount() {

        return this.amount;

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFrom() {

        return this.from;

    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {

        return this.to;

    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getHash() {

        return this.hash;

    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getContract_ret() {

        return this.contract_ret;

    }

    public void setContract_ret(String contract_ret) {
        this.contract_ret = contract_ret;
    }

    public Integer getDecimals() {

        return this.decimals;

    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    private static final class TrcTransferItemDTOBuilderImpl extends TrcTransferItemDTOBuilder<TrcTransferItemDTO, TrcTransferItemDTOBuilderImpl> {
        private TrcTransferItemDTOBuilderImpl() {
        }

        protected TrcTransferItemDTOBuilderImpl self() {
            return this;
        }

        public TrcTransferItemDTO build() {
            return new TrcTransferItemDTO(this);
        }
    }

    public static abstract class TrcTransferItemDTOBuilder<C extends TrcTransferItemDTO, B extends TrcTransferItemDTOBuilder<C, B>> {
        private BigDecimal amount;
        private String from;
        private String to;
        private String hash;
        private String contract_ret;
        private Integer decimals;

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public B from(String from) {
            this.from = from;
            return self();
        }

        public B to(String to) {
            this.to = to;
            return self();
        }

        public B hash(String hash) {
            this.hash = hash;
            return self();
        }

        public B contract_ret(String contract_ret) {
            this.contract_ret = contract_ret;
            return self();
        }

        public B decimals(Integer decimals) {
            this.decimals = decimals;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

