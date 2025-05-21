
package com.porn.client.withdraw.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.util.List;

@ApiModel("提现查询分页DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WithdrawQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("流水号")
    private String withdrawNo;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("账户ID列表")
    private List<Long> accountIdList;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("收款地址")
    private String receiveAddress;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private Integer status;

    @ApiModelProperty("备注")
    private String lkRemark;

}