
package com.porn.client.reward.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/reward/vo/RewardRuleVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */