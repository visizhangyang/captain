
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MyTeamTreeVo implements Serializable {
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
    
    @ApiModelProperty("父账户ID")
     private Long parentAccountId;


}


