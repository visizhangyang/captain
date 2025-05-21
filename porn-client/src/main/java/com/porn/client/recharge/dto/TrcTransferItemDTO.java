
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


    /* 16 */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setContract_ret(String contract_ret) {
        this.contract_ret = contract_ret;
    }

    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }


    protected boolean canEqual(Object other) {
        return other instanceof TrcTransferItemDTO;
    }



    /* 17 */
    protected TrcTransferItemDTO(TrcTransferItemDTOBuilder<?, ?> b) {
        this.amount = b.amount;
        this.from = b.from;
        this.to = b.to;
        this.hash = b.hash;
        this.contract_ret = b.contract_ret;
        this.decimals = b.decimals;
    }

    public static TrcTransferItemDTOBuilder<?, ?> builder() {
        return new TrcTransferItemDTOBuilderImpl();
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

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        private String hash;
        private String contract_ret;
        private Integer decimals;

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

    public TrcTransferItemDTO(BigDecimal amount, String from, String to, String hash, String contract_ret, Integer decimals) {
        /* 18 */
        this.amount = amount;
        this.from = from;
        this.to = to;
        this.hash = hash;
        this.contract_ret = contract_ret;
        this.decimals = decimals;

    }


    public TrcTransferItemDTO() {
    }



    public BigDecimal getAmount() {
        /* 23 */
        return this.amount;

    }


    public String getFrom() {
        /* 26 */
        return this.from;

    }


    public String getTo() {
        /* 29 */
        return this.to;

    }


    public String getHash() {
        /* 32 */
        return this.hash;

    }


    public String getContract_ret() {
        /* 35 */
        return this.contract_ret;

    }


    public Integer getDecimals() {
        /* 38 */
        return this.decimals;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/recharge/dto/TrcTransferItemDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */