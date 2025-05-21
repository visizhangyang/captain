
package com.porn.service.server.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataSizeUtil;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.NumberUtil;
import com.porn.client.server.api.ServerApiService;
import com.porn.client.server.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;



























@Service
 public class ServerApiServiceImpl implements ServerApiService {
    /*  29 */   private static final Logger log = LoggerFactory.getLogger(ServerApiServiceImpl.class);



       public static final int OSHI_WAIT_SECOND = 1000;





    public ServerInfoVo queryServerInfo() {
        /*  38 */
        return ServerInfoVo.builder()
/*  39 */.cpuInfo(queryCpuInfo())
/*  40 */.memoryInfo(queryMemoryInfo())
/*  41 */.jvmInfo(queryJvmInfo())
/*  42 */.systemInfo(querySystemInfo())
/*  43 */.diskInfos(queryDiskInfo())
/*  44 */.build();

    }



    public CpuInfoVo queryCpuInfo() {
        /*  48 */
        SystemInfo si = new SystemInfo();
        /*  49 */
        CentralProcessor processor = si.getHardware().getProcessor();
        /*  50 */
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        /*  51 */
        Util.sleep(1000L);
        /*  52 */
        long[] ticks = processor.getSystemCpuLoadTicks();
        /*  53 */
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        /*  54 */
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        /*  55 */
        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        /*  56 */
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        /*  57 */
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        /*  58 */
        long used = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        /*  59 */
        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        /*  60 */
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        /*  61 */
        long total = used + nice + cSys + idle + ioWait + irq + softIrq + steal;

        /*  63 */
        return CpuInfoVo.builder()
/*  64 */.cpuNum(processor.getLogicalProcessorCount())
/*  65 */.total(NumberUtil.round((total * 100L), 2).doubleValue())
/*  66 */.sys(NumberUtil.div((float) (cSys * 100L), (float) total, 2))
/*  67 */.used(NumberUtil.div((float) (used * 100L), (float) total, 2))
/*  68 */.wait(NumberUtil.div((float) (ioWait * 100L), (float) total, 2))
/*  69 */.free(NumberUtil.div((float) (idle * 100L), (float) total, 2))
/*  70 */.build();

    }



    public MemoryInfoVo queryMemoryInfo() {
        /*  74 */
        SystemInfo si = new SystemInfo();
        /*  75 */
        GlobalMemory globalMemory = si.getHardware().getMemory();
        /*  76 */
        return MemoryInfoVo.builder()
/*  77 */.total(NumberUtil.div((float) globalMemory.getTotal(), 1.07374182E9F, 2))
/*  78 */.used(NumberUtil.div(NumberUtil.sub((float) globalMemory.getTotal(), (float) globalMemory.getAvailable()), 1.07374182E9F, 2))
/*  79 */.free(NumberUtil.div((float) globalMemory.getAvailable(), 1.07374182E9F, 2))
/*  80 */.usage(NumberUtil.div(NumberUtil.sub((float) globalMemory.getTotal(), (float) globalMemory.getAvailable()) * 100.0D, (float) globalMemory.getTotal(), 2))
/*  81 */.build();

    }



    public JvmInfoVo queryJvmInfo() {
        /*  85 */
        Properties props = System.getProperties();
        /*  86 */
        double total = NumberUtil.div((float) Runtime.getRuntime().totalMemory(), 1048576.0F, 2);
        /*  87 */
        double max = NumberUtil.div((float) Runtime.getRuntime().maxMemory(), 1048576.0F, 2);
        /*  88 */
        double free = NumberUtil.div((float) Runtime.getRuntime().freeMemory(), 1048576.0F, 2);
        /*  89 */
        return JvmInfoVo.builder()
/*  90 */.total(total)
/*  91 */.max(max)
/*  92 */.free(free)
/*  93 */.used(NumberUtil.sub(total, free))
/*  94 */.usage(NumberUtil.div((total - free) * 100.0D, total, 2))
/*  95 */.version(props.getProperty("java.version"))
/*  96 */.home(props.getProperty("java.home"))
/*  97 */.name(ManagementFactory.getRuntimeMXBean().getVmName())
/*  98 */.startTime(DateUtil.format((Date) DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()), "yyyy-MM-dd HH:mm:ss"))
/*  99 */.runTime(DateUtil.formatBetween((Date) DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()), (Date) DateUtil.date()))
/* 100 */.inputArgs(ManagementFactory.getRuntimeMXBean().getInputArguments().toString())
/* 101 */.build();

    }



    public SystemInfoVo querySystemInfo() {
        /* 105 */
        Properties props = System.getProperties();
        /* 106 */
        return SystemInfoVo.builder()
/* 107 */.computerName(NetUtil.getLocalhost().getHostName())
/* 108 */.computerIp(NetUtil.getLocalhost().getHostAddress())
/* 109 */.osName(props.getProperty("os.name"))
/* 110 */.osArch(props.getProperty("os.arch"))
/* 111 */.userDir(props.getProperty("user.dir"))
/* 112 */.build();

    }



    public List<DiskInfoVo> queryDiskInfo() {
        /* 116 */
        SystemInfo si = new SystemInfo();
        /* 117 */
        OperatingSystem operatingSystem = si.getOperatingSystem();
        /* 118 */
        FileSystem fileSystem = operatingSystem.getFileSystem();
        /* 119 */
        List<OSFileStore> fileStoreList = fileSystem.getFileStores();
        /* 120 */
        List<DiskInfoVo> result = new ArrayList<>();
        /* 121 */
        for (OSFileStore fs : fileStoreList) {
            /* 122 */
            long free = fs.getUsableSpace();
            /* 123 */
            long total = fs.getTotalSpace();
            /* 124 */
            long used = total - free;








            /* 133 */
            DiskInfoVo diskInfo = DiskInfoVo.builder().dirName(fs.getMount()).type(fs.getType()).typeName(fs.getName()).total(DataSizeUtil.format(total)).free(DataSizeUtil.format(free)).used(DataSizeUtil.format(used)).usage((total != 0L) ? NumberUtil.div((float) (used * 100L), (float) total, 4) : 0.0D).build();
            /* 134 */
            result.add(diskInfo);

        }
        /* 136 */
        return result;

    }

}