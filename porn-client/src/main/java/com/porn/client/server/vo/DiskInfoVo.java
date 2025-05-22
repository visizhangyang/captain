
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
public class DiskInfoVo implements Serializable {
    @ApiModelProperty("盘符路径")
     private String dirName;
    @ApiModelProperty("文件系统类型")
     private String type;

    @ApiModelProperty("盘名称")
     private String typeName;

    @ApiModelProperty("总大小")
     private String total;

    @ApiModelProperty("剩余大小")
     private String free;

    @ApiModelProperty("已经使用量")
     private String used;

    @ApiModelProperty("资源的使用率")
     private double usage;


}


