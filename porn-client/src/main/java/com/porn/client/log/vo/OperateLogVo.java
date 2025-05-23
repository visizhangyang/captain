package com.porn.client.log.vo;

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
public class OperateLogVo extends BaseVo {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("请求方法")
    private String method;

    @ApiModelProperty("请求入口")
    private String action;

    @ApiModelProperty("参数")
    private String params;

    @ApiModelProperty("耗时")
    private Long timeConsume;

}

