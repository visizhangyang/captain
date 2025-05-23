package com.porn.client.merchant.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class MerchantRobotCreateDTO extends BaseDTO {

    @ApiModelProperty("保证金(小)")
    private BigDecimal ensureAmountMin;

    @ApiModelProperty("保证金(大)")
    private BigDecimal ensureAmountMax;

    @ApiModelProperty("费率(小)")
    private Integer rateRangeMin;

    @ApiModelProperty("费率(大)")
    private Integer rateRangeMax;

    @ApiModelProperty("会员级别")
    private List<Integer> memberLevelList;
    @ApiModelProperty("认证级别")
    private List<Integer> authLevelList;
    @ApiModelProperty("邮箱认证")
    private List<Integer> mailAuthList;
    @ApiModelProperty("手机认证")
    private List<Integer> phoneAuthList;
    @ApiModelProperty("KYC认证")
    private List<Integer> kycAuthList;
    @ApiModelProperty("地址认证")
    private List<Integer> addressAuthList;
    @ApiModelProperty("创建次数")
    private Integer createCount;

    protected MerchantRobotCreateDTO(MerchantRobotCreateDTOBuilder<?, ?> b) {
        super(b);
        this.ensureAmountMin = b.ensureAmountMin;
        this.ensureAmountMax = b.ensureAmountMax;
        this.rateRangeMin = b.rateRangeMin;
        this.rateRangeMax = b.rateRangeMax;
        this.memberLevelList = b.memberLevelList;
        this.authLevelList = b.authLevelList;
        this.mailAuthList = b.mailAuthList;
        this.phoneAuthList = b.phoneAuthList;
        this.kycAuthList = b.kycAuthList;
        this.addressAuthList = b.addressAuthList;
        this.createCount = b.createCount;
    }

    public MerchantRobotCreateDTO(BigDecimal ensureAmountMin, BigDecimal ensureAmountMax, Integer rateRangeMin, Integer rateRangeMax, List<Integer> memberLevelList, List<Integer> authLevelList, List<Integer> mailAuthList, List<Integer> phoneAuthList, List<Integer> kycAuthList, List<Integer> addressAuthList, Integer createCount) {

        this.ensureAmountMin = ensureAmountMin;
        this.ensureAmountMax = ensureAmountMax;
        this.rateRangeMin = rateRangeMin;
        this.rateRangeMax = rateRangeMax;
        this.memberLevelList = memberLevelList;
        this.authLevelList = authLevelList;
        this.mailAuthList = mailAuthList;
        this.phoneAuthList = phoneAuthList;
        this.kycAuthList = kycAuthList;
        this.addressAuthList = addressAuthList;
        this.createCount = createCount;

    }

    public MerchantRobotCreateDTO() {
    }

    public static MerchantRobotCreateDTOBuilder<?, ?> builder() {
        return new MerchantRobotCreateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof MerchantRobotCreateDTO;
    }

    public BigDecimal getEnsureAmountMin() {

        return this.ensureAmountMin;

    }

    public void setEnsureAmountMin(BigDecimal ensureAmountMin) {
        this.ensureAmountMin = ensureAmountMin;
    }

    public BigDecimal getEnsureAmountMax() {

        return this.ensureAmountMax;

    }

    public void setEnsureAmountMax(BigDecimal ensureAmountMax) {
        this.ensureAmountMax = ensureAmountMax;
    }

    public Integer getRateRangeMin() {

        return this.rateRangeMin;

    }

    public void setRateRangeMin(Integer rateRangeMin) {
        this.rateRangeMin = rateRangeMin;
    }

    public Integer getRateRangeMax() {

        return this.rateRangeMax;

    }

    public void setRateRangeMax(Integer rateRangeMax) {
        this.rateRangeMax = rateRangeMax;
    }

    public List<Integer> getMemberLevelList() {

        return this.memberLevelList;

    }

    public void setMemberLevelList(List<Integer> memberLevelList) {
        this.memberLevelList = memberLevelList;
    }

    public List<Integer> getAuthLevelList() {

        return this.authLevelList;

    }

    public void setAuthLevelList(List<Integer> authLevelList) {
        this.authLevelList = authLevelList;
    }

    public List<Integer> getMailAuthList() {

        return this.mailAuthList;

    }

    public void setMailAuthList(List<Integer> mailAuthList) {
        this.mailAuthList = mailAuthList;
    }

    public List<Integer> getPhoneAuthList() {

        return this.phoneAuthList;

    }

    public void setPhoneAuthList(List<Integer> phoneAuthList) {
        this.phoneAuthList = phoneAuthList;
    }

    public List<Integer> getKycAuthList() {

        return this.kycAuthList;

    }

    public void setKycAuthList(List<Integer> kycAuthList) {
        this.kycAuthList = kycAuthList;
    }

    public List<Integer> getAddressAuthList() {

        return this.addressAuthList;

    }

    public void setAddressAuthList(List<Integer> addressAuthList) {
        this.addressAuthList = addressAuthList;
    }

    public Integer getCreateCount() {

        return this.createCount;

    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }

    private static final class MerchantRobotCreateDTOBuilderImpl extends MerchantRobotCreateDTOBuilder<MerchantRobotCreateDTO, MerchantRobotCreateDTOBuilderImpl> {
        private MerchantRobotCreateDTOBuilderImpl() {
        }

        protected MerchantRobotCreateDTOBuilderImpl self() {
            return this;
        }

        public MerchantRobotCreateDTO build() {
            return new MerchantRobotCreateDTO(this);
        }
    }

    public static abstract class MerchantRobotCreateDTOBuilder<C extends MerchantRobotCreateDTO, B extends MerchantRobotCreateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private BigDecimal ensureAmountMin;
        private BigDecimal ensureAmountMax;
        private Integer rateRangeMin;
        private Integer rateRangeMax;
        private List<Integer> memberLevelList;
        private List<Integer> authLevelList;
        private List<Integer> mailAuthList;
        private List<Integer> phoneAuthList;
        private List<Integer> kycAuthList;
        private List<Integer> addressAuthList;
        private Integer createCount;

        public B ensureAmountMin(BigDecimal ensureAmountMin) {
            this.ensureAmountMin = ensureAmountMin;
            return self();
        }

        public B ensureAmountMax(BigDecimal ensureAmountMax) {
            this.ensureAmountMax = ensureAmountMax;
            return self();
        }

        public B rateRangeMin(Integer rateRangeMin) {
            this.rateRangeMin = rateRangeMin;
            return self();
        }

        public B rateRangeMax(Integer rateRangeMax) {
            this.rateRangeMax = rateRangeMax;
            return self();
        }

        public B memberLevelList(List<Integer> memberLevelList) {
            this.memberLevelList = memberLevelList;
            return self();
        }

        public B authLevelList(List<Integer> authLevelList) {
            this.authLevelList = authLevelList;
            return self();
        }

        public B mailAuthList(List<Integer> mailAuthList) {
            this.mailAuthList = mailAuthList;
            return self();
        }

        public B phoneAuthList(List<Integer> phoneAuthList) {
            this.phoneAuthList = phoneAuthList;
            return self();
        }

        public B kycAuthList(List<Integer> kycAuthList) {
            this.kycAuthList = kycAuthList;
            return self();
        }

        public B addressAuthList(List<Integer> addressAuthList) {
            this.addressAuthList = addressAuthList;
            return self();
        }

        public B createCount(Integer createCount) {
            this.createCount = createCount;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

