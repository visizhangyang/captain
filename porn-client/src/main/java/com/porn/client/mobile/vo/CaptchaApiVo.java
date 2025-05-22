
package com.porn.client.mobile.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;



@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class CaptchaApiVo
         implements Serializable
         {
    
    @ApiModelProperty("验证码token")
     private String captchaToken;
    
    @ApiModelProperty("验证码base64编码")
     private String captchaBase64;


    
}


