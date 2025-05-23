package com.porn.client.menu.vo;

import com.porn.client.common.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

