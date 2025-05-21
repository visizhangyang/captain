
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



    public void setGoodsId(Long goodsId) {
        /* 15 */
        this.goodsId = goodsId;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }


    protected boolean canEqual(Object other) {
        return other instanceof CreateOrderDTO;
    }



    /* 16 */
    public static CreateOrderDTOBuilder builder() {
        return new CreateOrderDTOBuilder();
    }

    public static class CreateOrderDTOBuilder {
        private Long goodsId;

        public CreateOrderDTOBuilder goodsId(Long goodsId) {
            this.goodsId = goodsId;
            return this;
        }

        private String walletCode;
        private String walletAddress;

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

    public CreateOrderDTO(Long goodsId, String walletCode, String walletAddress) {
        /* 17 */
        this.goodsId = goodsId;
        this.walletCode = walletCode;
        this.walletAddress = walletAddress;

    }


    public CreateOrderDTO() {
    }



    public Long getGoodsId() {
        /* 22 */
        return this.goodsId;

    }


    public String getWalletCode() {
        /* 25 */
        return this.walletCode;

    }


    public String getWalletAddress() {
        /* 28 */
        return this.walletAddress;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/dto/CreateOrderDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */