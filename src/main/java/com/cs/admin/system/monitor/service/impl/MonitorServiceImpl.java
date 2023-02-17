package com.cs.admin.system.monitor.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.cs.admin.common.util.CsFileUtil;
import com.cs.admin.common.util.IpUtil;
import com.cs.admin.common.util.ReportUtil;
import com.cs.admin.system.monitor.domain.vo.*;
import com.cs.admin.system.monitor.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 说明描叙
 * </p>
 *
 * @author free loop
 * @version 1.0
 * @since 2021/2/5 11:27
 */
@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {

    public static volatile SysMonitorVO monitorVOStore;

    @Override
    public SysMonitorVO monitor() {
        if (monitorVOStore != null){
            return monitorVOStore;
        }
        refresh();
        return monitorVOStore;
    }

    @Override
    public void refresh() {
        try {
            SystemInfo si = new SystemInfo();
            OperatingSystem os = si.getOperatingSystem();
            HardwareAbstractionLayer hal = si.getHardware();
            SysMonitorVO monitorVO = getSysInfo(os);
            monitorVO.setCpuInfo(getCpuInfo(hal.getProcessor()));
            monitorVO.setMemoryInfo(getMemoryInfo(hal.getMemory()));
            monitorVO.setSwapInfo(getSwapInfo(hal.getMemory()));
            monitorVO.setDiskInfo(getDiskInfo(os));
            monitorVOStore = monitorVO;
        } catch (Exception e) {
            log.error("获取监控信息异常:{}", ExceptionUtil.stacktraceToString(e));
            ReportUtil.throwEx(StrUtil.format("获取系统信息异常[{}]", e.getMessage()));
        }
    }

    private SysMonitorVO getSysInfo(OperatingSystem os) {
        // jvm 运行时间
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        Date date = new Date(time);
        // 计算项目运行时间
        String formatBetween = DateUtil.between(date, new Date(), DateUnit.HOUR) + "小时";
        SysMonitorVO monitorVO = new SysMonitorVO();
        monitorVO.setOs(os.toString());
        monitorVO.setDay(formatBetween);
        monitorVO.setIp(IpUtil.getLocalIp());
        return monitorVO;
    }


    /**
     * 获取系统磁盘问题
     *
     * @param os os
     * @return diskInfoVO
     */
    private DiskInfoVO getDiskInfo(OperatingSystem os) {

        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        String osName = System.getProperty("os.name");
        long available = 0, total = 0;
        for (OSFileStore fs : fsArray) {
            // windows 需要将所有磁盘分区累加，linux 和 mac 直接累加会出现磁盘重复的问题，待修复
            if (osName.toLowerCase().startsWith("win")) {
                available += fs.getUsableSpace();
                total += fs.getTotalSpace();
            } else {
                available = fs.getUsableSpace();
                total = fs.getTotalSpace();
                break;
            }
        }
        long used = total - available;
        DiskInfoVO infoVO = new DiskInfoVO();
        infoVO.setTotal(total > 0 ? CsFileUtil.getSize(total) : "?");
        infoVO.setAvailable(CsFileUtil.getSize(available));
        infoVO.setUsed(CsFileUtil.getSize(used));
        infoVO.setUsageRate(CsFileUtil.DF.format(used / (double) total * 100));
        return infoVO;
    }


    /**
     * 获取交换分区信息
     *
     * @param memory 内存
     * @return swapInfoVO
     */
    private SwapInfoVO getSwapInfo(GlobalMemory memory) {
        VirtualMemory virtualMemory = memory.getVirtualMemory();
        long total = virtualMemory.getSwapTotal();
        long used = virtualMemory.getSwapUsed();
        SwapInfoVO infoVO = new SwapInfoVO();
        infoVO.setTotal(FormatUtil.formatBytes(total));
        infoVO.setUsed(FormatUtil.formatBytes(used));
        infoVO.setAvailable(FormatUtil.formatBytes(total - used));
        if (used == 0) {
            infoVO.setUsageRate("0");
        } else {
            infoVO.setUsageRate(CsFileUtil.DF.format(used / (double) total * 100));
        }
        return infoVO;
    }


    /**
     * 查询内存信息
     *
     * @param memory 内存
     * @return memoryInfoVO
     */
    private MemoryInfoVO getMemoryInfo(GlobalMemory memory) {
        MemoryInfoVO infoVO = new MemoryInfoVO();
        infoVO.setTotal(FormatUtil.formatBytes(memory.getTotal()));
        infoVO.setAvailable(FormatUtil.formatBytes(memory.getAvailable()));
        infoVO.setUsed(FormatUtil.formatBytes(memory.getTotal() - memory.getAvailable()));
        infoVO.setUsageRate(CsFileUtil.DF.format((memory.getTotal() - memory.getAvailable()) / (double) memory.getTotal() * 100));
        return infoVO;
    }


    /**
     * 获取cpu信息
     *
     * @param processor 处理器
     * @return cpuInfoVO
     */
    private CpuInfoVO getCpuInfo(CentralProcessor processor) {
        CpuInfoVO infoVO = new CpuInfoVO();
        infoVO.setName(processor.getProcessorIdentifier().getName());
        infoVO.setPackages(processor.getPhysicalPackageCount() + "个物理CPU");
        infoVO.setCore(processor.getPhysicalProcessorCount() + "个物理核心");
        infoVO.setCoreNumber(processor.getPhysicalProcessorCount());
        infoVO.setLogic(processor.getLogicalProcessorCount() + "个逻辑CPU");
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long ioWait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + ioWait + irq + softIrq + steal;
        infoVO.setUsed(CsFileUtil.DF.format(100d * user / totalCpu + 100d * sys / totalCpu));
        infoVO.setAvailable(CsFileUtil.DF.format(100d * idle / totalCpu));
        return infoVO;
    }
}
