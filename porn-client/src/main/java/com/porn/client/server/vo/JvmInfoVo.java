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
public class JvmInfoVo implements Serializable {
    @ApiModelProperty("当前JVM占用的内存总数(M)")
    private double total;

    @ApiModelProperty("JVM最大可用内存总数(M)")
    private double max;

    @ApiModelProperty("JVM空闲内存(M)")
    private double free;

    @ApiModelProperty("使用量")
    private double used;

    @ApiModelProperty("使用率")
    private double usage;

    @ApiModelProperty("JDK版本")
    private String version;

    @ApiModelProperty("JDK路径")
    private String home;

    @ApiModelProperty("JAVA名称")
    private String name;

    @ApiModelProperty("JRE启动时间")
    private String startTime;

    @ApiModelProperty("JRE运行时间")
    private String runTime;

    @ApiModelProperty("运行参数")
    private String inputArgs;
}


