
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;





import java.io.Serializable;
import java.math.BigDecimal;





 public class LotteryApiRequestDTO
         implements Serializable
         {

    @ApiModelProperty("幸运序号")
     private Integer luckIndex;

    @ApiModelProperty("金额")
     private BigDecimal amount;



    public void setLuckIndex(Integer luckIndex) {
        /* 16 */
        this.luckIndex = luckIndex;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof LotteryApiRequestDTO;
    }



    /* 17 */
    public static LotteryApiRequestDTOBuilder builder() {
        return new LotteryApiRequestDTOBuilder();
    }

    public static class LotteryApiRequestDTOBuilder {
        private Integer luckIndex;

        public LotteryApiRequestDTOBuilder luckIndex(Integer luckIndex) {
            this.luckIndex = luckIndex;
            return this;
        }

        private BigDecimal amount;

        public LotteryApiRequestDTOBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public LotteryApiRequestDTO build() {
            return new LotteryApiRequestDTO(this.luckIndex, this.amount);
        }

    }

    public LotteryApiRequestDTO(Integer luckIndex, BigDecimal amount) {
        /* 18 */
        this.luckIndex = luckIndex;
        this.amount = amount;

    }


    public LotteryApiRequestDTO() {
    }



    public Integer getLuckIndex() {
        /* 23 */
        return this.luckIndex;

    }


    public BigDecimal getAmount() {
        /* 26 */
        return this.amount;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/dto/LotteryApiRequestDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */