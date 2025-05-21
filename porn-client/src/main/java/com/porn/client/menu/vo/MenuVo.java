
package com.porn.client.menu.vo;
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
public class MenuVo extends BaseVo {
    @ApiModelProperty("名称")
     private String name;
    @ApiModelProperty("图标类型, IconTypeEnum")
     private Integer iconType;
    @ApiModelProperty("图标路径")
     private String iconPath;
    @ApiModelProperty("图标全路径")
     private String iconUrl;

    @ApiModelProperty("url请求路径")
     private String urlPath;

    @ApiModelProperty("描述")
     private String description;

    @ApiModelProperty("排序值")
     private Integer sortNo;

    @ApiModelProperty("父ID")
     private Long parentId;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/menu/vo/MenuVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */