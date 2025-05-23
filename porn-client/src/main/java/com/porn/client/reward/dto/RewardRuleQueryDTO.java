package com.porn.client.reward.dto;

import com.porn.client.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class RewardRuleQueryDTO extends BaseDTO {

    @ApiModelProperty("规则类型")
    private Integer ruleType;

    @ApiModelProperty("语言类型")
    private Integer langType;

    @ApiModelProperty("语言名称")
    private String langTypeName;

    @ApiModelProperty("规则类型")
    private List<Integer> ruleTypeList;

    protected RewardRuleQueryDTO(RewardRuleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.ruleType = b.ruleType;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
        this.ruleTypeList = b.ruleTypeList;
    }

    public RewardRuleQueryDTO(Integer ruleType, Integer langType, String langTypeName, List<Integer> ruleTypeList) {

        this.ruleType = ruleType;
        this.langType = langType;
        this.langTypeName = langTypeName;
        this.ruleTypeList = ruleTypeList;

    }

    public RewardRuleQueryDTO() {
    }

    public static RewardRuleQueryDTOBuilder<?, ?> builder() {
        return new RewardRuleQueryDTOBuilderImpl();
    }

    protected boolean canEqual(Object other) {
        return other instanceof RewardRuleQueryDTO;
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

    public String getLangTypeName() {

        return this.langTypeName;

    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }

    public List<Integer> getRuleTypeList() {

        return this.ruleTypeList;

    }

    public void setRuleTypeList(List<Integer> ruleTypeList) {
        this.ruleTypeList = ruleTypeList;
    }

    private static final class RewardRuleQueryDTOBuilderImpl extends RewardRuleQueryDTOBuilder<RewardRuleQueryDTO, RewardRuleQueryDTOBuilderImpl> {
        private RewardRuleQueryDTOBuilderImpl() {
        }

        protected RewardRuleQueryDTOBuilderImpl self() {
            return this;
        }

        public RewardRuleQueryDTO build() {
            return new RewardRuleQueryDTO(this);
        }
    }

    public static abstract class RewardRuleQueryDTOBuilder<C extends RewardRuleQueryDTO, B extends RewardRuleQueryDTOBuilder<C, B>> extends BaseDTO.BaseDTOBuilder<C, B> {
        private Integer ruleType;
        private Integer langType;
        private String langTypeName;
        private List<Integer> ruleTypeList;

        public B ruleType(Integer ruleType) {
            this.ruleType = ruleType;
            return self();
        }

        public B langType(Integer langType) {
            this.langType = langType;
            return self();
        }

        public B langTypeName(String langTypeName) {
            this.langTypeName = langTypeName;
            return self();
        }

        public B ruleTypeList(List<Integer> ruleTypeList) {
            this.ruleTypeList = ruleTypeList;
            return self();
        }

        protected abstract B self();

        public abstract C build();

    }

}

