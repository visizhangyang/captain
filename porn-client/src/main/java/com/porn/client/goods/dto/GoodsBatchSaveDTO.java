package com.porn.client.goods.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

public class GoodsBatchSaveDTO extends BaseDTO {
    @ApiModelProperty("商户id")
    private Long merchantId;
    @ApiModelProperty("商户名称")
    private String merchantName;
    @ApiModelProperty("商户头像")
    private String merchantAvatar;

    @ApiModelProperty("最小金额")
    private Integer minAmount;

    @ApiModelProperty("最大金额")
    private Integer maxAmount;

    @ApiModelProperty("创建次数")
    private Integer createCount;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    protected GoodsBatchSaveDTO(GoodsBatchSaveDTOBuilder<?, ?> b) {
        super(b);
        this.merchantId = b.merchantId;
        this.merchantName = b.merchantName;
        this.merchantAvatar = b.merchantAvatar;
        this.minAmount = b.minAmount;
        this.maxAmount = b.maxAmount;
        this.createCount = b.createCount;
        this.accountId = b.accountId;
        this.accountName = b.accountName;
    }

    public GoodsBatchSaveDTO(Long merchantId, String merchantName, String merchantAvatar, Integer minAmount, Integer maxAmount, Integer createCount, Long accountId, String accountName) {

        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantAvatar = merchantAvatar;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.createCount = createCount;
        this.accountId = accountId;
        this.accountName = accountName;

    }

    public GoodsBatchSaveDTO() {
    }

    public static GoodsBatchSaveDTOBuilder<?, ?> builder() {
        return new GoodsBatchSaveDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof GoodsBatchSaveDTO;
    }

    public Long getMerchantId() {

        return this.merchantId;

    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {

        return this.merchantName;

    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantAvatar() {

        return this.merchantAvatar;

    }

    public void setMerchantAvatar(String merchantAvatar) {
        this.merchantAvatar = merchantAvatar;
    }

    public Integer getMinAmount() {

        return this.minAmount;

    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {

        return this.maxAmount;

    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getCreateCount() {

        return this.createCount;

    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }

    public Long getAccountId() {

        return this.accountId;

    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {

        return this.accountName;

    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    private static final class GoodsBatchSaveDTOBuilderImpl extends GoodsBatchSaveDTOBuilder<GoodsBatchSaveDTO, GoodsBatchSaveDTOBuilderImpl> {
        private GoodsBatchSaveDTOBuilderImpl() {
        }

        protected GoodsBatchSaveDTOBuilderImpl self() {
            return this;
        }

        public GoodsBatchSaveDTO build() {
            return new GoodsBatchSaveDTO(this);
        }
    }

    public static abstract class GoodsBatchSaveDTOBuilder<C extends GoodsBatchSaveDTO, B extends GoodsBatchSaveDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Long merchantId;
        private String merchantName;
        private String merchantAvatar;
        private Integer minAmount;
        private Integer maxAmount;
        private Integer createCount;
        private Long accountId;
        private String accountName;

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        public B merchantName(String merchantName) {
            this.merchantName = merchantName;
            return self();
        }

        public B merchantAvatar(String merchantAvatar) {
            this.merchantAvatar = merchantAvatar;
            return self();
        }

        public B minAmount(Integer minAmount) {
            this.minAmount = minAmount;
            return self();
        }

        public B maxAmount(Integer maxAmount) {
            this.maxAmount = maxAmount;
            return self();
        }

        public B createCount(Integer createCount) {
            this.createCount = createCount;
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
}

