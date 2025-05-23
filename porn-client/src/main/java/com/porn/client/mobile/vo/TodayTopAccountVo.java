package com.porn.client.mobile.vo;

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
public class TodayTopAccountVo extends BaseVo {
    @ApiModelProperty("账户id")
    private Long accountId;

    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("账户头像")
    private String accountAvatar;

    @ApiModelProperty("账户头像")
    private String accountAvatarUrl;

    @ApiModelProperty("今日搬砖总金额")
    private BigDecimal todayTotalAmount;

    @ApiModelProperty("今日搬砖总佣金")
    private BigDecimal todayTotalFree;

}

