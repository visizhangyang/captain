
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/mobile/vo/LoginAccountVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */