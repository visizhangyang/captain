
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
 public class RoleVo extends BaseVo {
    
    @ApiModelProperty("用户名")
     private String name;
    
    @ApiModelProperty("描述")
     private String description;
    
    @ApiModelProperty("状态")
     private Integer status;


    
}


