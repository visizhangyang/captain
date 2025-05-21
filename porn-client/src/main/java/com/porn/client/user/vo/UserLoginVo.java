
package com.porn.client.user.vo;
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
public class UserLoginVo implements Serializable {
    @ApiModelProperty("主键")
     private Long id;
    @ApiModelProperty("姓名")
     private String name;
    
    @ApiModelProperty("昵称")
     private String nickName;
    
    @ApiModelProperty("头像")
     private String avatar;
    
    @ApiModelProperty("头像路径")
     private String avatarUrl;
    
    @ApiModelProperty("签名")
     private String sign;
    
    @ApiModelProperty("令牌")
     private String token;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/vo/UserLoginVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */