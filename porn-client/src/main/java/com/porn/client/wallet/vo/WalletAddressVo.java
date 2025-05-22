
package com.porn.client.wallet.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class WalletAddressVo extends BaseVo {
    @ApiModelProperty("地址名称")
     private String name;

    @ApiModelProperty("编码")
     private String code;

    @ApiModelProperty("地址")
     private String address;

    @ApiModelProperty("地址状态")
     private Integer addressStatus;

    @ApiModelProperty("锁定时间")
     private LocalDateTime lockTime;

    @ApiModelProperty("状态, 1-启用, 0-禁用")
     private Integer status;

    @ApiModelProperty("备注")
     private String remark;


}


