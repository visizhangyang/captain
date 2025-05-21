
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TaskProgressVo implements Serializable {
    @ApiModelProperty("规则名称")
     private String name;
    @ApiModelProperty("子名称")
     private String subName;
    @ApiModelProperty("规则类型")
     private Integer ruleType;

    @ApiModelProperty("规则图片访问地址")
     private String ruleImgUrl;

    @ApiModelProperty("当前数量")
     private BigDecimal curNum;

    @ApiModelProperty("总金额")
     private BigDecimal totalNum;

    @ApiModelProperty("次数")
     private Integer rewardNum;

    @ApiModelProperty("过期时间")
     private String expireTime;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/TaskProgressVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */