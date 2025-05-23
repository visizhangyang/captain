package com.porn.client.server.api;

import com.porn.client.server.vo.*;

import java.util.List;

public interface ServerApiService {
    ServerInfoVo queryServerInfo();

    CpuInfoVo queryCpuInfo();

    MemoryInfoVo queryMemoryInfo();

    JvmInfoVo queryJvmInfo();

    SystemInfoVo querySystemInfo();

    List<DiskInfoVo> queryDiskInfo();
}

