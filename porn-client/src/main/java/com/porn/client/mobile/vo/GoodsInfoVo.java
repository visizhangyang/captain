package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GoodsInfoVo implements Serializable {
    @ApiModelProperty("在线的账户数量")
    private Long inlineAccountCount;

    @ApiModelProperty("重点关注, 默认不关注, 1-关注, 0-不关注 com.porn.client.common.enums.EnableStatusEnum")
    private Integer keynoteFollow;

    @ApiModelProperty("是否搬砖中, 0-否, 1-是")
    private Integer planing;

    @ApiModelProperty("开始搬砖, 1-开启, 0-关闭, 默认1")
    private Integer enableWork;

    @ApiModelProperty("商品信息列表")
    private List<GoodsItemVo> goodsItemList;

}
