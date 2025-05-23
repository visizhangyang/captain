package com.porn.client.withdraw.dto;

import com.porn.client.common.dto.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel("代理提现查询分页DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProxyWithdrawQueryPageDTO extends BasePageDTO {
    @ApiModelProperty("流水号")
    private String withdrawNo;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("钱包编码")
    private String walletCode;

    @ApiModelProperty("账户名称")
    private String lkAccountName;

    @ApiModelProperty("收款地址")
    private String receiveAddress;

    @ApiModelProperty("状态, WithdrawStatusEnum")
    private Integer status;

}

