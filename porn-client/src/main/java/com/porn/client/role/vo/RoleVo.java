
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/role/vo/RoleVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */