package com.porn.client.server.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CpuInfoVo implements Serializable {
    @ApiModelProperty("核心数")
    private int cpuNum;

    @ApiModelProperty("CPU总的使用率")
    private double total;

    @ApiModelProperty("CPU系统使用率")
    private double sys;

    @ApiModelProperty("CPU用户使用率")
    private double used;

    @ApiModelProperty("CPU当前等待率")
    private double wait;

    @ApiModelProperty("CPU当前空闲率")
    private double free;

}

