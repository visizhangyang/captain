
package com.porn.client.mobile.dto;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.account.vo.AccountVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class CmdRequestDTO implements Serializable {

    @ApiModelProperty("api名称")
     private String api;

    @ApiModelProperty("版本")
     private String version;

    @ApiModelProperty("会话token")
     private String token;

    @ApiModelProperty("数据部分")
     private String data;

    @ApiModelProperty("用户信息")
     private AccountVo accountVo;

    @ApiModelProperty("扩展数据")
     private MobileExtDTO mobileExtDTO;



}

