
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;



import com.porn.client.account.vo.AccountVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class LoginAccountVo extends AccountVo {
    
    @ApiModelProperty("会话token值")
     private String token;
    
    @ApiModelProperty("是否领取过红包, 0-否, 1-是")
     private Integer receiveRedPack;
    
    @ApiModelProperty("钱包编码(默认钱包类型)")
     private String walletCode;


}


