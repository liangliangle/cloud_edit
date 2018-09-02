package com.doubi.edit.service.impl;

import com.doubi.edit.dto.MonitorInfoBean;
import com.doubi.edit.service.IMonitorService;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;

/**
 * @ClessName MonitorServiceImpl
 * @Author liangliang
 * @Date 2018/8/31 16:11
 * @Version 1.0
 */
@Service
public class MonitorServiceImpl implements IMonitorService {
  //可以设置长些，防止读到运行此次系统检查时的cpu占用率，就不准了
  private static final int CPUTIME = 5000;

  private static final int PERCENT = 100;

  private static final int FAULTLENGTH = 10;

  /** */
  /**
   * 获得当前的监控对象.
   *
   * @return 返回构造好的监控对象
   * @throws Exception
   * @author amg     * Creation date: 2008-4-25 - 上午10:45:08
   */
  public MonitorInfoBean getMonitorInfoBean() throws Exception {
    int kb = 1024;

    // 可使用内存
    long totalMemory = Runtime.getRuntime().totalMemory() / kb / kb;
    // 剩余内存
    long freeMemory = Runtime.getRuntime().freeMemory() / kb / kb;
    // 最大可使用内存
    long maxMemory = Runtime.getRuntime().maxMemory() / kb / kb;

    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();

    // 操作系统
    String osName = System.getProperty("os.name");
    // 总的物理内存
    long totalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb / kb;
    // 剩余的物理内存
    long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize() / kb / kb;
    // 已使用的物理内存
    long usedMemory = (osmxb.getTotalPhysicalMemorySize() - osmxb
            .getFreePhysicalMemorySize())
            / kb / kb;

    // 获得线程总数
    ThreadGroup parentThread;
    for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
            .getParent() != null; parentThread = parentThread.getParent())
      ;
    int totalThread = parentThread.activeCount();


    // 构造返回对象
    MonitorInfoBean infoBean = new MonitorInfoBean();
    infoBean.setFreeMemory(freeMemory);
    infoBean.setFreePhysicalMemorySize(freePhysicalMemorySize);
    infoBean.setMaxMemory(maxMemory);
    infoBean.setOsName(osName);
    infoBean.setTotalMemory(totalMemory);
    infoBean.setTotalMemorySize(totalMemorySize);
    infoBean.setTotalThread(totalThread);
    infoBean.setUsedMemory(usedMemory);
    return infoBean;
  }


  /** */
  /**
   * 测试方法.
   *
   * @param args
   * @throws Exception
   * @author amg     * Creation date: 2008-4-30 - 下午04:47:29
   */
  public static void main(String[] args) throws Exception {
    IMonitorService service = new MonitorServiceImpl();
    MonitorInfoBean monitorInfo = service.getMonitorInfoBean();
    System.out.println("cpu占有率=" + monitorInfo.getCpuRatio());

    System.out.println("可使用内存=" + monitorInfo.getTotalMemory());
    System.out.println("剩余内存=" + monitorInfo.getFreeMemory());
    System.out.println("最大可使用内存=" + monitorInfo.getMaxMemory());

    System.out.println("操作系统=" + monitorInfo.getOsName());
    System.out.println("总的物理内存=" + monitorInfo.getTotalMemorySize() + "MB");
    System.out.println("剩余的物理内存=" + monitorInfo.getFreeMemory() + "MB");
    System.out.println("已使用的物理内存=" + monitorInfo.getUsedMemory() + "MB");
    System.out.println("线程总数=" + monitorInfo.getTotalThread());
  }

}
