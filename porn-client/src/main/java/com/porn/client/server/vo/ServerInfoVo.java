package com.porn.client.server.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ServerInfoVo implements Serializable {
    @ApiModelProperty("CPU信息")
    private CpuInfoVo cpuInfo;

    @ApiModelProperty("内存信息")
    private MemoryInfoVo memoryInfo;

    @ApiModelProperty("JVM信息")
    private JvmInfoVo jvmInfo;

    @ApiModelProperty("系统信息")
    private SystemInfoVo systemInfo;

    @ApiModelProperty("磁盘信息")
    private List<DiskInfoVo> diskInfos;

}

