
package com.porn.client.role.vo;
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
 public class RoleMenuVo
         extends BaseVo
         {

    @ApiModelProperty("角色ID")
     private Long roleId;

    @ApiModelProperty("菜单ID")
     private Long menuId;


}


