
package com.porn.client.merchant.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;

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

    
    /* 17 */
    public void setEnsureAmountMin(BigDecimal ensureAmountMin) {
        this.ensureAmountMin = ensureAmountMin;
    }

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

    public void setEnsureAmountMax(BigDecimal ensureAmountMax) {
        this.ensureAmountMax = ensureAmountMax;
    }

    public void setRateRangeMin(Integer rateRangeMin) {
        this.rateRangeMin = rateRangeMin;
    }

    public void setRateRangeMax(Integer rateRangeMax) {
        this.rateRangeMax = rateRangeMax;
    }

    public void setMemberLevelList(List<Integer> memberLevelList) {
        this.memberLevelList = memberLevelList;
    }

    public void setAuthLevelList(List<Integer> authLevelList) {
        this.authLevelList = authLevelList;
    }

    public void setMailAuthList(List<Integer> mailAuthList) {
        this.mailAuthList = mailAuthList;
    }

    public void setPhoneAuthList(List<Integer> phoneAuthList) {
        this.phoneAuthList = phoneAuthList;
    }

    public void setKycAuthList(List<Integer> kycAuthList) {
        this.kycAuthList = kycAuthList;
    }

    public void setAddressAuthList(List<Integer> addressAuthList) {
        this.addressAuthList = addressAuthList;
    }

    public void setCreateCount(Integer createCount) {
        this.createCount = createCount;
    }


    protected boolean canEqual(Object other) {
        return other instanceof MerchantRobotCreateDTO;
    }



    /* 18 */
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

    public static MerchantRobotCreateDTOBuilder<?, ?> builder() {
        return new MerchantRobotCreateDTOBuilderImpl();
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

    public MerchantRobotCreateDTO(BigDecimal ensureAmountMin, BigDecimal ensureAmountMax, Integer rateRangeMin, Integer rateRangeMax, List<Integer> memberLevelList, List<Integer> authLevelList, List<Integer> mailAuthList, List<Integer> phoneAuthList, List<Integer> kycAuthList, List<Integer> addressAuthList, Integer createCount) {
        /* 19 */
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

    
    
    public BigDecimal getEnsureAmountMin() {
        /* 24 */
        return this.ensureAmountMin;
        
    }

    
    public BigDecimal getEnsureAmountMax() {
        /* 27 */
        return this.ensureAmountMax;
        
    }

    
    public Integer getRateRangeMin() {
        /* 30 */
        return this.rateRangeMin;
        
    }

    
    public Integer getRateRangeMax() {
        /* 33 */
        return this.rateRangeMax;
        
    }

    
    public List<Integer> getMemberLevelList() {
        /* 36 */
        return this.memberLevelList;
        
    }

    
    public List<Integer> getAuthLevelList() {
        /* 39 */
        return this.authLevelList;
        
    }

    
    public List<Integer> getMailAuthList() {
        /* 42 */
        return this.mailAuthList;
        
    }

    
    public List<Integer> getPhoneAuthList() {
        /* 45 */
        return this.phoneAuthList;
        
    }

    
    public List<Integer> getKycAuthList() {
        /* 48 */
        return this.kycAuthList;
        
    }

    
    public List<Integer> getAddressAuthList() {
        /* 51 */
        return this.addressAuthList;
        
    }

    
    public Integer getCreateCount() {
        /* 54 */
        return this.createCount;
        
    }
    
}


