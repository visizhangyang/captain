package com.porn.client.server.vo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;


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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/server/vo/ServerInfoVo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */