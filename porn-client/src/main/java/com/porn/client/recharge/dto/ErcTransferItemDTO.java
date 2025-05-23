package com.porn.client.recharge.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ErcTransferItemDTO implements Serializable {

    @ApiModelProperty("金额, 需要去除tokenDecimal位后才是金额")
    private BigDecimal value;

    @ApiModelProperty("源地址")
    private String from;

    @ApiModelProperty("接收地址, 也就是我们钱包地址")
    private String to;

    @ApiModelProperty("交易hash")
    private String hash;

    @ApiModelProperty("精度")
    private Integer tokenDecimal;

    protected ErcTransferItemDTO(ErcTransferItemDTOBuilder<?, ?> b) {
        this.value = b.value;
        this.from = b.from;
        this.to = b.to;
        this.hash = b.hash;
        this.tokenDecimal = b.tokenDecimal;
    }

    public ErcTransferItemDTO(BigDecimal value, String from, String to, String hash, Integer tokenDecimal) {

        this.value = value;
        this.from = from;
        this.to = to;
        this.hash = hash;
        this.tokenDecimal = tokenDecimal;

    }

    public ErcTransferItemDTO() {
    }

    public static ErcTransferItemDTOBuilder<?, ?> builder() {
        return new ErcTransferItemDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof ErcTransferItemDTO;
    }

    public BigDecimal getValue() {

        return this.value;

    }

    public void setValue(BigDecimal value) {
        this.value = value;
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

    public Integer getTokenDecimal() {

        return this.tokenDecimal;

    }

    public void setTokenDecimal(Integer tokenDecimal) {
        this.tokenDecimal = tokenDecimal;
    }

    private static final class ErcTransferItemDTOBuilderImpl extends ErcTransferItemDTOBuilder<ErcTransferItemDTO, ErcTransferItemDTOBuilderImpl> {
        private ErcTransferItemDTOBuilderImpl() {
        }

        protected ErcTransferItemDTOBuilderImpl self() {
            return this;
        }

        public ErcTransferItemDTO build() {
            return new ErcTransferItemDTO(this);
        }
    }

    public static abstract class ErcTransferItemDTOBuilder<C extends ErcTransferItemDTO, B extends ErcTransferItemDTOBuilder<C, B>> {
        private BigDecimal value;
        private String from;
        private String to;
        private String hash;
        private Integer tokenDecimal;

        public B value(BigDecimal value) {
            this.value = value;
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

        public B tokenDecimal(Integer tokenDecimal) {
            this.tokenDecimal = tokenDecimal;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

