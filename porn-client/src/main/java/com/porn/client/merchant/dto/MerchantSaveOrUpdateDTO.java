package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class MerchantSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("商户名称")
    private String name;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("保证金")
    private BigDecimal ensureAmount;

    @ApiModelProperty("费率范围")
    private String rateRange;

    @ApiModelProperty("地区名称")
    private String areaName;

    @ApiModelProperty("会员级别")
    private Integer memberLevel;

    @ApiModelProperty("会员级别名称")
    private String memberLevelName;

    @ApiModelProperty("认证级别")
    private Integer authLevel;
    @ApiModelProperty("认证级别名称")
    private String authLevelName;
    @ApiModelProperty("邮箱认证")
    private Integer mailAuth;
    @ApiModelProperty("手机认证")
    private Integer phoneAuth;
    @ApiModelProperty("kyc认证")
    private Integer kycAuth;
    @ApiModelProperty("地址认证")
    private Integer addressAuth;
    @ApiModelProperty("会员状态")
    private Integer status;
    @ApiModelProperty("商户类型, MerchantTypeEnum")
    private Integer merchantType;
    @ApiModelProperty("商户标签")
    private String merchantTag;

    protected MerchantSaveOrUpdateDTO(MerchantSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.avatar = b.avatar;
        this.ensureAmount = b.ensureAmount;
        this.rateRange = b.rateRange;
        this.areaName = b.areaName;
        this.memberLevel = b.memberLevel;
        this.memberLevelName = b.memberLevelName;
        this.authLevel = b.authLevel;
        this.authLevelName = b.authLevelName;
        this.mailAuth = b.mailAuth;
        this.phoneAuth = b.phoneAuth;
        this.kycAuth = b.kycAuth;
        this.addressAuth = b.addressAuth;
        this.status = b.status;
        this.merchantType = b.merchantType;
        this.merchantTag = b.merchantTag;
    }

    public MerchantSaveOrUpdateDTO(String name, String avatar, BigDecimal ensureAmount, String rateRange, String areaName, Integer memberLevel, String memberLevelName, Integer authLevel, String authLevelName, Integer mailAuth, Integer phoneAuth, Integer kycAuth, Integer addressAuth, Integer status, Integer merchantType, String merchantTag) {

        this.name = name;
        this.avatar = avatar;
        this.ensureAmount = ensureAmount;
        this.rateRange = rateRange;
        this.areaName = areaName;
        this.memberLevel = memberLevel;
        this.memberLevelName = memberLevelName;
        this.authLevel = authLevel;
        this.authLevelName = authLevelName;
        this.mailAuth = mailAuth;
        this.phoneAuth = phoneAuth;
        this.kycAuth = kycAuth;
        this.addressAuth = addressAuth;
        this.status = status;
        this.merchantType = merchantType;
        this.merchantTag = merchantTag;

    }

    public MerchantSaveOrUpdateDTO() {
    }

    public static MerchantSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new MerchantSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantSaveOrUpdateDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {

        return this.avatar;

    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getEnsureAmount() {

        return this.ensureAmount;

    }

    public void setEnsureAmount(BigDecimal ensureAmount) {
        this.ensureAmount = ensureAmount;
    }

    public String getRateRange() {

        return this.rateRange;

    }

    public void setRateRange(String rateRange) {
        this.rateRange = rateRange;
    }

    public String getAreaName() {

        return this.areaName;

    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getMemberLevel() {

        return this.memberLevel;

    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberLevelName() {

        return this.memberLevelName;

    }

    public void setMemberLevelName(String memberLevelName) {
        this.memberLevelName = memberLevelName;
    }

    public Integer getAuthLevel() {

        return this.authLevel;

    }

    public void setAuthLevel(Integer authLevel) {
        this.authLevel = authLevel;
    }

    public String getAuthLevelName() {

        return this.authLevelName;

    }

    public void setAuthLevelName(String authLevelName) {
        this.authLevelName = authLevelName;
    }

    public Integer getMailAuth() {

        return this.mailAuth;

    }

    public void setMailAuth(Integer mailAuth) {
        this.mailAuth = mailAuth;
    }

    public Integer getPhoneAuth() {

        return this.phoneAuth;

    }

    public void setPhoneAuth(Integer phoneAuth) {
        this.phoneAuth = phoneAuth;
    }

    public Integer getKycAuth() {

        return this.kycAuth;

    }

    public void setKycAuth(Integer kycAuth) {
        this.kycAuth = kycAuth;
    }

    public Integer getAddressAuth() {

        return this.addressAuth;

    }

    public void setAddressAuth(Integer addressAuth) {
        this.addressAuth = addressAuth;
    }

    public Integer getStatus() {

        return this.status;

    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMerchantType() {

        return this.merchantType;

    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public String getMerchantTag() {

        return this.merchantTag;

    }

    public void setMerchantTag(String merchantTag) {
        this.merchantTag = merchantTag;
    }

    private static final class MerchantSaveOrUpdateDTOBuilderImpl extends MerchantSaveOrUpdateDTOBuilder<MerchantSaveOrUpdateDTO, MerchantSaveOrUpdateDTOBuilderImpl> {
        private MerchantSaveOrUpdateDTOBuilderImpl() {
        }

        protected MerchantSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public MerchantSaveOrUpdateDTO build() {
            return new MerchantSaveOrUpdateDTO(this);
        }
    }

    public static abstract class MerchantSaveOrUpdateDTOBuilder<C extends MerchantSaveOrUpdateDTO, B extends MerchantSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private String avatar;
        private BigDecimal ensureAmount;
        private String rateRange;
        private String areaName;
        private Integer memberLevel;
        private String memberLevelName;
        private Integer authLevel;
        private String authLevelName;
        private Integer mailAuth;
        private Integer phoneAuth;
        private Integer kycAuth;
        private Integer addressAuth;
        private Integer status;
        private Integer merchantType;
        private String merchantTag;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B avatar(String avatar) {
            this.avatar = avatar;
            return self();
        }

        public B ensureAmount(BigDecimal ensureAmount) {
            this.ensureAmount = ensureAmount;
            return self();
        }

        public B rateRange(String rateRange) {
            this.rateRange = rateRange;
            return self();
        }

        public B areaName(String areaName) {
            this.areaName = areaName;
            return self();
        }

        public B memberLevel(Integer memberLevel) {
            this.memberLevel = memberLevel;
            return self();
        }

        public B memberLevelName(String memberLevelName) {
            this.memberLevelName = memberLevelName;
            return self();
        }

        public B authLevel(Integer authLevel) {
            this.authLevel = authLevel;
            return self();
        }

        public B authLevelName(String authLevelName) {
            this.authLevelName = authLevelName;
            return self();
        }

        public B mailAuth(Integer mailAuth) {
            this.mailAuth = mailAuth;
            return self();
        }

        public B phoneAuth(Integer phoneAuth) {
            this.phoneAuth = phoneAuth;
            return self();
        }

        public B kycAuth(Integer kycAuth) {
            this.kycAuth = kycAuth;
            return self();
        }

        public B addressAuth(Integer addressAuth) {
            this.addressAuth = addressAuth;
            return self();
        }

        public B status(Integer status) {
            this.status = status;
            return self();
        }

        public B merchantType(Integer merchantType) {
            this.merchantType = merchantType;
            return self();
        }

        public B merchantTag(String merchantTag) {
            this.merchantTag = merchantTag;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }
}

