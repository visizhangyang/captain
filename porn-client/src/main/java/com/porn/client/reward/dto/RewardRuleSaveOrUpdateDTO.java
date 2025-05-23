package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

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

    public RewardRuleSaveOrUpdateDTO(String name, String subName, Integer ruleType, Integer langType, String ruleImg, BigDecimal totalAmount, Integer rewardNum) {

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

    public static RewardRuleSaveOrUpdateDTOBuilder<?, ?> builder() {
        return new RewardRuleSaveOrUpdateDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RewardRuleSaveOrUpdateDTO;
    }

    public String getName() {

        return this.name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {

        return this.subName;

    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public Integer getRuleType() {

        return this.ruleType;

    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getLangType() {

        return this.langType;

    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public String getRuleImg() {

        return this.ruleImg;

    }

    public void setRuleImg(String ruleImg) {
        this.ruleImg = ruleImg;
    }

    public BigDecimal getTotalAmount() {

        return this.totalAmount;

    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getRewardNum() {

        return this.rewardNum;

    }

    public void setRewardNum(Integer rewardNum) {
        this.rewardNum = rewardNum;
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
        private Integer langType;
        private String ruleImg;
        private BigDecimal totalAmount;
        private Integer rewardNum;

        public B name(String name) {
            this.name = name;
            return self();
        }

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
}

