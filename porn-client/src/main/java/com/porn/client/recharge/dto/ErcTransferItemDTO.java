
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


    /* 16 */
    public void setValue(BigDecimal value) {
        this.value = value;
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

    public void setTokenDecimal(Integer tokenDecimal) {
        this.tokenDecimal = tokenDecimal;
    }


    protected boolean canEqual(Object other) {
        return other instanceof ErcTransferItemDTO;
    }



    /* 17 */
    protected ErcTransferItemDTO(ErcTransferItemDTOBuilder<?, ?> b) {
        this.value = b.value;
        this.from = b.from;
        this.to = b.to;
        this.hash = b.hash;
        this.tokenDecimal = b.tokenDecimal;
    }

    public static ErcTransferItemDTOBuilder<?, ?> builder() {
        return new ErcTransferItemDTOBuilderImpl();
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

        public B value(BigDecimal value) {
            this.value = value;
            return self();
        }

        private String to;
        private String hash;
        private Integer tokenDecimal;

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

    public ErcTransferItemDTO(BigDecimal value, String from, String to, String hash, Integer tokenDecimal) {
        /* 18 */
        this.value = value;
        this.from = from;
        this.to = to;
        this.hash = hash;
        this.tokenDecimal = tokenDecimal;

    }


    public ErcTransferItemDTO() {
    }



    public BigDecimal getValue() {
        /* 23 */
        return this.value;

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


    public Integer getTokenDecimal() {
        /* 35 */
        return this.tokenDecimal;

    }

}


