
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BaseDTO;

import java.math.BigDecimal;

public class RewardRuleSaveOrUpdateDTO extends BaseDTO {
    @ApiModelProperty("规则名称")
     private String name;

    @ApiModelProperty("子名称")
     private String subName;

    @ApiModelProperty("规则类型")
     private Integer ruleType;

    @ApiModelProperty("语言类型")
     private Integer langType;

    @ApiModelProperty("规则图片")
     private String ruleImg;

    @ApiModelProperty("总金额")
     private BigDecimal totalAmount;

    @ApiModelProperty("次数")
     private Integer rewardNum;


    /* 16 */
    public void setName(String name) {
        this.name = name;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setRuleImg(String ruleImg) {
        this.ruleImg = ruleImg;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setRewardNum(Integer rewardNum) {
        this.rewardNum = rewardNum;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RewardRuleSaveOrUpdateDTO;
    }



    /* 17 */
    protected RewardRuleSaveOrUpdateDTO(RewardRuleSaveOrUpdateDTOBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.subName = b.subName;
        this.ruleType = b.ruleType;
        this.langType = b.langType;
        this.ruleImg = b.ruleImg;
        this.totalAmount = b.totalAmount;
        this.rewardNum = b.rewardNum;
    }

    public static RewardRuleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RewardRuleSaveOrUpdateDTOBuilderImpl();
    }

    private static final class RewardRuleSaveOrUpdateDTOBuilderImpl extends RewardRuleSaveOrUpdateDTOBuilder<RewardRuleSaveOrUpdateDTO, RewardRuleSaveOrUpdateDTOBuilderImpl> {
        private RewardRuleSaveOrUpdateDTOBuilderImpl() {
        }

        protected RewardRuleSaveOrUpdateDTOBuilderImpl self() {
            return this;
        }

        public RewardRuleSaveOrUpdateDTO build() {
            return new RewardRuleSaveOrUpdateDTO(this);
        }
    }

    public static abstract class RewardRuleSaveOrUpdateDTOBuilder<C extends RewardRuleSaveOrUpdateDTO, B extends RewardRuleSaveOrUpdateDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private String name;
        private String subName;
        private Integer ruleType;

        public B name(String name) {
            this.name = name;
            return self();
        }

        private Integer langType;
        private String ruleImg;
        private BigDecimal totalAmount;
        private Integer rewardNum;

        public B subName(String subName) {
            this.subName = subName;
            return self();
        }

        public B ruleType(Integer ruleType) {
            this.ruleType = ruleType;
            return self();
        }

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B ruleImg(String ruleImg) {
            this.ruleImg = ruleImg;
            return self();
        }

        public B totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return self();
        }

        public B rewardNum(Integer rewardNum) {
            this.rewardNum = rewardNum;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

    public RewardRuleSaveOrUpdateDTO(String name, String subName, Integer ruleType, Integer langType, String ruleImg, BigDecimal totalAmount, Integer rewardNum) {
        /* 18 */
        this.name = name;
        this.subName = subName;
        this.ruleType = ruleType;
        this.langType = langType;
        this.ruleImg = ruleImg;
        this.totalAmount = totalAmount;
        this.rewardNum = rewardNum;

    }


    public RewardRuleSaveOrUpdateDTO() {
    }



    public String getName() {
        /* 23 */
        return this.name;

    }


    public String getSubName() {
        /* 26 */
        return this.subName;

    }


    public Integer getRuleType() {
        /* 29 */
        return this.ruleType;

    }


    public Integer getLangType() {
        /* 32 */
        return this.langType;

    }


    public String getRuleImg() {
        /* 35 */
        return this.ruleImg;

    }


    public BigDecimal getTotalAmount() {
        /* 38 */
        return this.totalAmount;

    }


    public Integer getRewardNum() {
        /* 41 */
        return this.rewardNum;

    }
}


