package com.porn.client.reward.vo;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RewardRuleVo extends BaseVo {
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

    @ApiModelProperty("规则图片访问地址")
    private String ruleImgUrl;

    @ApiModelProperty("总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("次数")
    private Integer rewardNum;

}

