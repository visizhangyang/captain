
package com.porn.client.log.vo;
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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/log/vo/OperateLogVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */