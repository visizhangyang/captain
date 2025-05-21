
package com.porn.client.reward.dto;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.common.dto.BaseDTO;


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


    /* 16 */
    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public void setLangType(Integer langType) {
        this.langType = langType;
    }

    public void setLangTypeName(String langTypeName) {
        this.langTypeName = langTypeName;
    }

    public void setRuleTypeList(List<Integer> ruleTypeList) {
        this.ruleTypeList = ruleTypeList;
    }


    protected boolean canEqual(Object other) {
        return other instanceof RewardRuleQueryDTO;
    }



    /* 17 */
    protected RewardRuleQueryDTO(RewardRuleQueryDTOBuilder<?, ?> b) {
        super(b);
        this.ruleType = b.ruleType;
        this.langType = b.langType;
        this.langTypeName = b.langTypeName;
        this.ruleTypeList = b.ruleTypeList;
    }

    public static RewardRuleQueryDTOBuilder<?, ?> builder() {
        return new RewardRuleQueryDTOBuilderImpl();
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

        public B ruleType(Integer ruleType) {
            this.ruleType = ruleType;
            return self();
        }

        private String langTypeName;
        private List<Integer> ruleTypeList;

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

    public RewardRuleQueryDTO(Integer ruleType, Integer langType, String langTypeName, List<Integer> ruleTypeList) {
        /* 18 */
        this.ruleType = ruleType;
        this.langType = langType;
        this.langTypeName = langTypeName;
        this.ruleTypeList = ruleTypeList;

    }


    public RewardRuleQueryDTO() {
    }



    public Integer getRuleType() {
        /* 23 */
        return this.ruleType;

    }


    public Integer getLangType() {
        /* 26 */
        return this.langType;

    }


    public String getLangTypeName() {
        /* 29 */
        return this.langTypeName;

    }


    public List<Integer> getRuleTypeList() {
        /* 32 */
        return this.ruleTypeList;

    }

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/dto/RewardRuleQueryDTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */