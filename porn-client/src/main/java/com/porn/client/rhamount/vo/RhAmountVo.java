package com.porn.client.rhamount.vo;

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
public class RhAmountVo
        extends BaseVo {

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("排序号")
    private Integer sortNo;

}

