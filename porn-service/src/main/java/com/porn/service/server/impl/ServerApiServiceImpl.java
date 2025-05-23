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
    public static final int OSHI_WAIT_SECOND = 1000;
    private static final Logger log = LoggerFactory.getLogger(ServerApiServiceImpl.class);

    public ServerInfoVo queryServerInfo() {

        return ServerInfoVo.builder()
                .cpuInfo(queryCpuInfo())
                .memoryInfo(queryMemoryInfo())
                .jvmInfo(queryJvmInfo())
                .systemInfo(querySystemInfo())
                .diskInfos(queryDiskInfo())
                .build();

    }


    public CpuInfoVo queryCpuInfo() {

        SystemInfo si = new SystemInfo();

        CentralProcessor processor = si.getHardware().getProcessor();

        long[] prevTicks = processor.getSystemCpuLoadTicks();

        Util.sleep(1000L);

        long[] ticks = processor.getSystemCpuLoadTicks();

        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];

        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];

        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];

        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];

        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];

        long used = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];

        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];

        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];

        long total = used + nice + cSys + idle + ioWait + irq + softIrq + steal;


        return CpuInfoVo.builder()
                .cpuNum(processor.getLogicalProcessorCount())
                .total(NumberUtil.round((total * 100L), 2).doubleValue())
                .sys(NumberUtil.div((float) (cSys * 100L), (float) total, 2))
                .used(NumberUtil.div((float) (used * 100L), (float) total, 2))
                .wait(NumberUtil.div((float) (ioWait * 100L), (float) total, 2))
                .free(NumberUtil.div((float) (idle * 100L), (float) total, 2))
                .build();

    }


    public MemoryInfoVo queryMemoryInfo() {

        SystemInfo si = new SystemInfo();

        GlobalMemory globalMemory = si.getHardware().getMemory();

        return MemoryInfoVo.builder()
                .total(NumberUtil.div((float) globalMemory.getTotal(), 1.07374182E9F, 2))
                .used(NumberUtil.div(NumberUtil.sub((float) globalMemory.getTotal(), (float) globalMemory.getAvailable()), 1.07374182E9F, 2))
                .free(NumberUtil.div((float) globalMemory.getAvailable(), 1.07374182E9F, 2))
                .usage(NumberUtil.div(NumberUtil.sub((float) globalMemory.getTotal(), (float) globalMemory.getAvailable()) * 100.0D, (float) globalMemory.getTotal(), 2))
                .build();

    }


    public JvmInfoVo queryJvmInfo() {

        Properties props = System.getProperties();

        double total = NumberUtil.div((float) Runtime.getRuntime().totalMemory(), 1048576.0F, 2);

        double max = NumberUtil.div((float) Runtime.getRuntime().maxMemory(), 1048576.0F, 2);

        double free = NumberUtil.div((float) Runtime.getRuntime().freeMemory(), 1048576.0F, 2);

        return JvmInfoVo.builder()
                .total(total)
                .max(max)
                .free(free)
                .used(NumberUtil.sub(total, free))
                .usage(NumberUtil.div((total - free) * 100.0D, total, 2))
                .version(props.getProperty("java.version"))
                .home(props.getProperty("java.home"))
                .name(ManagementFactory.getRuntimeMXBean().getVmName())
                .startTime(DateUtil.format((Date) DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()), "yyyy-MM-dd HH:mm:ss"))
                .runTime(DateUtil.formatBetween((Date) DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()), (Date) DateUtil.date()))
                .inputArgs(ManagementFactory.getRuntimeMXBean().getInputArguments().toString())
                .build();

    }


    public SystemInfoVo querySystemInfo() {

        Properties props = System.getProperties();

        return SystemInfoVo.builder()
                .computerName(NetUtil.getLocalhost().getHostName())
                .computerIp(NetUtil.getLocalhost().getHostAddress())
                .osName(props.getProperty("os.name"))
                .osArch(props.getProperty("os.arch"))
                .userDir(props.getProperty("user.dir"))
                .build();

    }


    public List<DiskInfoVo> queryDiskInfo() {

        SystemInfo si = new SystemInfo();

        OperatingSystem operatingSystem = si.getOperatingSystem();

        FileSystem fileSystem = operatingSystem.getFileSystem();

        List<OSFileStore> fileStoreList = fileSystem.getFileStores();

        List<DiskInfoVo> result = new ArrayList<>();

        for (OSFileStore fs : fileStoreList) {

            long free = fs.getUsableSpace();

            long total = fs.getTotalSpace();

            long used = total - free;


            DiskInfoVo diskInfo = DiskInfoVo.builder().dirName(fs.getMount()).type(fs.getType()).typeName(fs.getName()).total(DataSizeUtil.format(total)).free(DataSizeUtil.format(free)).used(DataSizeUtil.format(used)).usage((total != 0L) ? NumberUtil.div((float) (used * 100L), (float) total, 4) : 0.0D).build();

            result.add(diskInfo);

        }

        return result;

    }

}