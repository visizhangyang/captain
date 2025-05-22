
package com.porn.client.user.vo;
import io.swagger.annotations.ApiModelProperty;

import com.porn.client.common.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class UserVo extends BaseVo {
    @ApiModelProperty("用户名")
     private String name;
    
    @ApiModelProperty("昵称")
     private String nickName;
    
    @ApiModelProperty("头像")
     private String avatar;
    
    @ApiModelProperty("头像")
     private String avatarUrl;
    
    @ApiModelProperty("状态")
     private Integer status;
    
    @ApiModelProperty("个性签名")
     private String sign;


}


