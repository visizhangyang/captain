
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;
import java.math.BigDecimal;





 public class CreateRechargeApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("钱包编码")
     private String walletCode;

    @ApiModelProperty("充值金额")
     private BigDecimal amount;



    public void setWalletCode(String walletCode) {
        /* 16 */
        this.walletCode = walletCode;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof CreateRechargeApiRequestDTO;
    }



    /* 17 */
    public static CreateRechargeApiRequestDTOBuilder builder() {
        return new CreateRechargeApiRequestDTOBuilder();
    }

    public static class CreateRechargeApiRequestDTOBuilder {
        private String walletCode;

        public CreateRechargeApiRequestDTOBuilder walletCode(String walletCode) {
            this.walletCode = walletCode;
            return this;
        }

        private BigDecimal amount;

        public CreateRechargeApiRequestDTOBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public CreateRechargeApiRequestDTO build() {
            return new CreateRechargeApiRequestDTO(this.walletCode, this.amount);
        }

    }

    public CreateRechargeApiRequestDTO(String walletCode, BigDecimal amount) {
        /* 18 */
        this.walletCode = walletCode;
        this.amount = amount;

    }


    public CreateRechargeApiRequestDTO() {
    }



    public String getWalletCode() {
        /* 23 */
        return this.walletCode;

    }


    public BigDecimal getAmount() {
        /* 26 */
        return this.amount;

    }

}


