package com.porn.client.stream.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@ApiModel("流水记录VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class StreamVo extends BaseVo {
    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("操作前总余额")
    private BigDecimal beforeTotalBalance;

    @ApiModelProperty("操作前可用余额")
    private BigDecimal beforeAvailableBalance;

    @ApiModelProperty("操作前冻结余额")
    private BigDecimal beforeFreezeBalance;

    @ApiModelProperty("操作后总余额")
    private BigDecimal afterTotalBalance;

    @ApiModelProperty("操作后可用余额")
    private BigDecimal afterAvailableBalance;

    @ApiModelProperty("操作后冻结余额")
    private BigDecimal afterFreezeBalance;

    @ApiModelProperty("业务ID")
    private Long bizId;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("类型, StreamTypeEnum")
    private Integer type;

    @ApiModelProperty("标识, StreamTypeEnum")
    private Integer flag;

}