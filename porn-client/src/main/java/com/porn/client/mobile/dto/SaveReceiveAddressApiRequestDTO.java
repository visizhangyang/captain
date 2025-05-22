
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


    /* 15 */
    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }


    protected boolean canEqual(Object other) {
        return other instanceof SaveReceiveAddressApiRequestDTO;
    }



    /* 16 */
    public static SaveReceiveAddressApiRequestDTOBuilder builder() {
        return new SaveReceiveAddressApiRequestDTOBuilder();
    }

    public static class SaveReceiveAddressApiRequestDTOBuilder {
        private String walletCode;
        private String receiveAddress;
        private String promotionCode;
        private String qq;

        public SaveReceiveAddressApiRequestDTOBuilder walletCode(String walletCode) {
            this.walletCode = walletCode;
            return this;
        }

        private String wechat;
        private String phone;
        private String nickName;
        private Boolean defaultAddress;

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

    public SaveReceiveAddressApiRequestDTO(String walletCode, String receiveAddress, String promotionCode, String qq, String wechat, String phone, String nickName, Boolean defaultAddress) {
        /* 17 */
        this.walletCode = walletCode;
        this.receiveAddress = receiveAddress;
        this.promotionCode = promotionCode;
        this.qq = qq;
        this.wechat = wechat;
        this.phone = phone;
        this.nickName = nickName;
        this.defaultAddress = defaultAddress;

    }




    public String getWalletCode() {
        /* 22 */
        return this.walletCode;

    }


    public String getReceiveAddress() {
        /* 25 */
        return this.receiveAddress;

    }


    public String getPromotionCode() {
        /* 28 */
        return this.promotionCode;

    }


    public String getQq() {
        /* 31 */
        return this.qq;

    }


    public String getWechat() {
        /* 34 */
        return this.wechat;

    }


    public String getPhone() {
        /* 37 */
        return this.phone;

    }


    public String getNickName() {
        /* 40 */
        return this.nickName;

    }

    @ApiModelProperty("默认收款地址")
    /* 42 */ private Boolean defaultAddress = Boolean.TRUE;

    public Boolean getDefaultAddress() {
        /* 43 */
        return this.defaultAddress;

    }



    public SaveReceiveAddressApiRequestDTO() {
    }
}


