
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;


import java.math.BigDecimal;

public class GoodsSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("商户头像")
    private String merchantAvatar;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("费率(%)")
    private BigDecimal rate;

    @ApiModelProperty("佣金")
    private BigDecimal freeAmount;

    @ApiModelProperty("商品状态, GoodsStatusEnum")
    private Integer goodsStatus;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantAvatar(String merchantAvatar) {
        this.merchantAvatar = merchantAvatar;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setFreeAmount(BigDecimal freeAmount) {
        this.freeAmount = freeAmount;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsSaveOrUpdateDTO;
    }



    protected GoodsSaveOrUpdateDTO(GoodsSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.merchantName = b.merchantName;
        this.merchantAvatar = b.merchantAvatar;
        this.amount = b.amount;
        this.rate = b.rate;
        this.freeAmount = b.freeAmount;
        this.goodsStatus = b.goodsStatus;
        this.accountId = b.accountId;
        this.accountName = b.accountName;
    }

    public static GoodsSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new GoodsSaveOrUpdateDTOBuilderImpl();
    }

    private static final class GoodsSaveOrUpdateDTOBuilderImpl extends GoodsSaveOrUpdateDTOBuilder<GoodsSaveOrUpdateDTO, GoodsSaveOrUpdateDTOBuilderImpl> {
        private GoodsSaveOrUpdateDTOBuilderImpl() {
        }

        protected GoodsSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public GoodsSaveOrUpdateDTO build() {
            return new GoodsSaveOrUpdateDTO(this);
        }
    }

    public static abstract class GoodsSaveOrUpdateDTOBuilder<C extends GoodsSaveOrUpdateDTO, B extends GoodsSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;
        private String merchantName;
        private String merchantAvatar;
        private BigDecimal amount;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private BigDecimal rate;
        private BigDecimal freeAmount;
        private Integer goodsStatus;
        private Long accountId;
        private String accountName;

        public B merchantName(String merchantName) {
            this.merchantName = merchantName;
            return self();
        }

        public B merchantAvatar(String merchantAvatar) {
            this.merchantAvatar = merchantAvatar;
            return self();
        }

        public B amount(BigDecimal amount) {
            this.amount = amount;
            return self();
        }

        public B rate(BigDecimal rate) {
            this.rate = rate;
            return self();
        }

        public B freeAmount(BigDecimal freeAmount) {
            this.freeAmount = freeAmount;
            return self();
        }

        public B goodsStatus(Integer goodsStatus) {
            this.goodsStatus = goodsStatus;
            return self();
        }

        public B accountId(Long accountId) {
            this.accountId = accountId;
            return self();
        }

        public B accountName(String accountName) {
            this.accountName = accountName;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public GoodsSaveOrUpdateDTO(Long merchantId, String merchantName, String merchantAvatar, BigDecimal amount, BigDecimal rate, BigDecimal freeAmount, Integer goodsStatus, Long accountId, String accountName) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantAvatar = merchantAvatar;
        this.amount = amount;
        this.rate = rate;
        this.freeAmount = freeAmount;
        this.goodsStatus = goodsStatus;
        this.accountId = accountId;
        this.accountName = accountName;
    }

    public GoodsSaveOrUpdateDTO() {
    }

    public Long getMerchantId() {
        return this.merchantId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getMerchantAvatar() {
        return this.merchantAvatar;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getRate() {
        return this.rate;
    }

    public BigDecimal getFreeAmount() {
        return this.freeAmount;
    }

    public Integer getGoodsStatus() {
        return this.goodsStatus;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public String getAccountName() {
        return this.accountName;
    }
}


