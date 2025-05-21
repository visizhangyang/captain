
package com.porn.client.mobile.vo;
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/TodayTopAccountVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */