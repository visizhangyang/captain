
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class MultistageLevelFreeVo extends BaseVo {
    @ApiModelProperty("账户ID")
     private Long accountId;

    @ApiModelProperty("账户名称")
     private String accountName;

    @ApiModelProperty("头像")
     private String avatar;

    @ApiModelProperty("头像")
     private String avatarUrl;

    @ApiModelProperty("累计搬砖金额")
     private BigDecimal workTotalAmount;

    @ApiModelProperty("累计搬砖佣金金额")
     private BigDecimal freeTotalAmount;

    @ApiModelProperty("注册时间")
     private LocalDateTime registerTime;

}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/MultistageLevelFreeVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */