
package com.porn.client.server.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
 public class SystemInfoVo implements Serializable {

    @ApiModelProperty("服务器名称")
     private String computerName;

    @ApiModelProperty("服务器Ip")
     private String computerIp;

    @ApiModelProperty("项目路径")
     private String userDir;

    @ApiModelProperty("操作系统")
     private String osName;

    @ApiModelProperty("系统架构")
     private String osArch;


}


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/server/vo/SystemInfoVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */