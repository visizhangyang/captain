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


/* Location:              /Users/wh/Documents/个人资料/work/20250507/UPeak-3.3.0/lib/porn-client-3.3.0.jar!/com/porn/client/server/api/ServerApiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */