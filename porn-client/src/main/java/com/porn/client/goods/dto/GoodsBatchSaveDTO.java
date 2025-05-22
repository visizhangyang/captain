
package com.porn.client.goods.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

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


    /* 16 */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMerchantAvatar(String merchantAvatar) {
        this.merchantAvatar = merchantAvatar;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    protected boolean canEqual(Object other) {
        return other instanceof GoodsBatchSaveDTO;
    }



    /* 17 */
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

    public static GoodsBatchSaveDTOBuilder<?, ?> builder() {
        return new GoodsBatchSaveDTOBuilderImpl();
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

        public B merchantId(Long merchantId) {
            this.merchantId = merchantId;
            return self();
        }

        private Integer maxAmount;
        private Integer createCount;
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

    public GoodsBatchSaveDTO(Long merchantId, String merchantName, String merchantAvatar, Integer minAmount, Integer maxAmount, Integer createCount, Long accountId, String accountName) {
        /* 18 */
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



    public Long getMerchantId() {
        /* 23 */
        return this.merchantId;

    }


    public String getMerchantName() {
        /* 26 */
        return this.merchantName;

    }


    public String getMerchantAvatar() {
        /* 29 */
        return this.merchantAvatar;

    }


    public Integer getMinAmount() {
        /* 32 */
        return this.minAmount;

    }


    public Integer getMaxAmount() {
        /* 35 */
        return this.maxAmount;

    }


    public Integer getCreateCount() {
        /* 38 */
        return this.createCount;

    }


    public Long getAccountId() {
        /* 41 */
        return this.accountId;

    }


    public String getAccountName() {
        /* 44 */
        return this.accountName;

    }
}


