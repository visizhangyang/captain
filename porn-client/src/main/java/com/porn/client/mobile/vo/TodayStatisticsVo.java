package com.porn.client.mobile.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class TodayStatisticsVo implements Serializable {
    @ApiModelProperty("今日总交易额")
    private BigDecimal totalAmount;
    @ApiModelProperty("今日佣金")
    private BigDecimal freeAmount;
    @ApiModelProperty("今日成功订单数")
    private Integer orderCount;

    @ApiModelProperty("今日交易中数量")
    private Integer tradingCount;

    @ApiModelProperty("今日待收金额")
    private BigDecimal waitReceiveAmount;

    @ApiModelProperty("总数")
    private Integer totalCount;

    @ApiModelProperty("最后一个订单时间")
    private String lastOrderTime;

    @ApiModelProperty("未读公告数量")
    private Integer unReadNoticeCount;

}

