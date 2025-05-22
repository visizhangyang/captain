
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


