
package com.porn.client.account.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class AccountWalletVo extends BaseVo {

    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("钱包编码")
     private String walletCode;

    @ApiModelProperty("钱包名称")
     private String walletName;

    @ApiModelProperty("地址")
     private String address;

    @ApiModelProperty("是否删除")
     private Integer delFlag;


}


