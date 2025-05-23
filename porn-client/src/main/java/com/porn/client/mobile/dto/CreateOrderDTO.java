package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class CreateOrderDTO implements Serializable {

    @ApiModelProperty("商品ID")
    private Long goodsId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("钱包地址")
    private String walletAddress;

    public CreateOrderDTO(Long goodsId, String walletCode, String walletAddress) {

        this.goodsId = goodsId;
        this.walletCode = walletCode;
        this.walletAddress = walletAddress;

    }

    public CreateOrderDTO() {
    }

    public static CreateOrderDTOBuilder builder() {
        return new CreateOrderDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof CreateOrderDTO;
    }

    public Long getGoodsId() {

        return this.goodsId;

    }

    public void setGoodsId(Long goodsId) {

        this.goodsId = goodsId;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getWalletAddress() {

        return this.walletAddress;

    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    public static class CreateOrderDTOBuilder {
        private Long goodsId;
        private String walletCode;
        private String walletAddress;

        public CreateOrderDTOBuilder goodsId(Long goodsId) {
            this.goodsId = goodsId;
            return this;
        }

        public CreateOrderDTOBuilder walletCode(String walletCode) {
            this.walletCode = walletCode;
            return this;
        }

        public CreateOrderDTOBuilder walletAddress(String walletAddress) {
            this.walletAddress = walletAddress;
            return this;
        }

        public CreateOrderDTO build() {
            return new CreateOrderDTO(this.goodsId, this.walletCode, this.walletAddress);
        }

    }

}

