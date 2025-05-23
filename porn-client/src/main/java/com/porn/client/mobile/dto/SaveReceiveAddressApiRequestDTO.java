package com.porn.client.mobile.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SaveReceiveAddressApiRequestDTO implements Serializable {
    @ApiModelProperty("钱包编码")
    private String walletCode;
    @ApiModelProperty("收款地址")
    private String receiveAddress;

    @ApiModelProperty("推广码")
    private String promotionCode;

    @ApiModelProperty("QQ号码")
    private String qq;

    @ApiModelProperty("微信号码")
    private String wechat;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("默认收款地址")
    private Boolean defaultAddress = Boolean.TRUE;

    public SaveReceiveAddressApiRequestDTO(String walletCode, String receiveAddress, String promotionCode, String qq, String wechat, String phone, String nickName, Boolean defaultAddress) {

        this.walletCode = walletCode;
        this.receiveAddress = receiveAddress;
        this.promotionCode = promotionCode;
        this.qq = qq;
        this.wechat = wechat;
        this.phone = phone;
        this.nickName = nickName;
        this.defaultAddress = defaultAddress;

    }

    public SaveReceiveAddressApiRequestDTO() {
    }

    public static SaveReceiveAddressApiRequestDTOBuilder builder() {
        return new SaveReceiveAddressApiRequestDTOBuilder();
    }

    protected boolean canEqual(Object other) {
        return other instanceof SaveReceiveAddressApiRequestDTO;
    }

    public String getWalletCode() {

        return this.walletCode;

    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public String getReceiveAddress() {

        return this.receiveAddress;

    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getPromotionCode() {

        return this.promotionCode;

    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getQq() {

        return this.qq;

    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {

        return this.wechat;

    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {

        return this.phone;

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {

        return this.nickName;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Boolean getDefaultAddress() {

        return this.defaultAddress;

    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public static class SaveReceiveAddressApiRequestDTOBuilder {
        private String walletCode;
        private String receiveAddress;
        private String promotionCode;
        private String qq;
        private String wechat;
        private String phone;
        private String nickName;
        private Boolean defaultAddress;

        public SaveReceiveAddressApiRequestDTOBuilder walletCode(String walletCode) {
            this.walletCode = walletCode;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder receiveAddress(String receiveAddress) {
            this.receiveAddress = receiveAddress;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder promotionCode(String promotionCode) {
            this.promotionCode = promotionCode;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder qq(String qq) {
            this.qq = qq;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder wechat(String wechat) {
            this.wechat = wechat;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public SaveReceiveAddressApiRequestDTOBuilder defaultAddress(Boolean defaultAddress) {
            this.defaultAddress = defaultAddress;
            return this;
        }

        public SaveReceiveAddressApiRequestDTO build() {
            return new SaveReceiveAddressApiRequestDTO(this.walletCode, this.receiveAddress, this.promotionCode, this.qq, this.wechat, this.phone, this.nickName, this.defaultAddress);
        }

    }
}

