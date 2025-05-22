package com.porn.client.goods.vo;
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
public class GoodsVo extends BaseVo {
    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("商户头像")
    private String merchantAvatar;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("费率(%)")
    private BigDecimal rate;

    @ApiModelProperty("佣金")
    private BigDecimal freeAmount;

    @ApiModelProperty("账户ID")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

}


