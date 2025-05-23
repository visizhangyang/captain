package com.porn.client.goods.vo;

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
public class GoodsRuleVo extends BaseVo {

    @ApiModelProperty("商户id")
    private Long merchantId;

    @ApiModelProperty("最小商品数量")
    private Integer minGoodsCount;

    @ApiModelProperty("最大商品数量")
    private Integer maxGoodsCount;

    @ApiModelProperty("最小金额")
    private BigDecimal minAmount;

    @ApiModelProperty("最大金额")
    private BigDecimal maxAmount;

    @ApiModelProperty("状态, 1-启用, 0-禁用 com.porn.client.common.enums.EnableStatusEnum")
    private Integer status;

}

