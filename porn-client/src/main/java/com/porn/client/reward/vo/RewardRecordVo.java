
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
public class RewardRecordVo extends BaseVo {
    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("操作前可用次数")
     private BigDecimal beforeAvailableCount;

    @ApiModelProperty("操作后可用次数")
     private BigDecimal afterAvailableCount;

    @ApiModelProperty("操作后次数")
     private BigDecimal operateCount;

    @ApiModelProperty("类型, RewardRecordTypeEnum")
     private Integer type;

    @ApiModelProperty("业务类型")
     private Integer bizType;

    @ApiModelProperty("业务ID")
     private String bizId;


}


