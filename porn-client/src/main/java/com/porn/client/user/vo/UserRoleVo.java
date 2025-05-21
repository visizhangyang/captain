
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
 public class UserRoleVo
         extends BaseVo
         {
    
    @ApiModelProperty("用户ID")
     private Long userId;
    
    @ApiModelProperty("角色ID")
     private Long roleId;


    
}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/user/vo/UserRoleVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */