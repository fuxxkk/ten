package test;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.hyperic.sigar.*;
import org.junit.Test;

import java.util.stream.Stream;

public class TestSys {

    @Test
    public void test1() throws SigarException, InterruptedException {
        long start = System.currentTimeMillis();
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem(); //获取内存对象

        System.out.println("内存总量:    " + mem.getTotal() / 1024L + "K av");
        // 当前内存使用量
        System.out.println("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
        // 当前内存剩余量
        System.out.println("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");

        //jvm信息
        Runtime r = Runtime.getRuntime();
        System.out.println("JVM可以使用的总内存:    " + r.totalMemory());
        System.out.println("JVM可以使用的剩余内存:    " + r.freeMemory());
        System.out.println("JVM可以已使用的内存:    " + (r.totalMemory() - r.freeMemory()));
        System.out.println("JVM可以使用的处理器个数:    " + r.availableProcessors());

        //cpu信息
        CpuPerc[] cpuList = sigar.getCpuPercList();
        Stream.of(cpuList).forEach(cpu -> {
            System.out.println("==========================");
            System.out.println("CPU用户使用率:    " + CpuPerc.format(cpu.getUser()));// 用户使用率
            System.out.println("CPU系统使用率:    " + CpuPerc.format(cpu.getSys()));// 系统使用率
            System.out.println("CPU当前等待率:    " + CpuPerc.format(cpu.getWait()));// 当前等待率
            System.out.println("CPU当前错误率:    " + CpuPerc.format(cpu.getNice()));//
            System.out.println("CPU当前空闲率:    " + CpuPerc.format(cpu.getIdle()));// 当前空闲率
            System.out.println("CPU总的使用率:    " + CpuPerc.format(cpu.getCombined()));// 总的使用率
        });

        //获取硬盘
        FileSystem[] fileSystemList = sigar.getFileSystemList();
        long start2 = System.currentTimeMillis();
        Observable.fromArray(fileSystemList).observeOn(Schedulers.io()).forEach(fs -> {
            long start1 = System.currentTimeMillis();
            FileSystemUsage usage = null;
            try {
                usage = sigar.getFileSystemUsage(fs.getDirName());
            } catch (SigarException e) {
                e.printStackTrace();
            }
            if (fs.getType() == 2) {
                System.out.println("*********************************************");
                // 文件系统总大小
                System.out.println(fs.getDevName() + "总大小:    " + usage.getTotal() + "KB");
                // 文件系统剩余大小
                System.out.println(fs.getDevName() + "剩余大小:    " + usage.getFree() + "KB");
                // 文件系统可用大小
                System.out.println(fs.getDevName() + "可用大小:    " + usage.getAvail() + "KB");
                // 文件系统已经使用量
                System.out.println(fs.getDevName() + "已经使用量:    " + usage.getUsed() + "KB");
                double usePercent = usage.getUsePercent() * 100D;
                // 文件系统资源的利用率
                System.out.println(fs.getDevName() + "资源的利用率:    " + usePercent + "%");
            }
            long end1 = System.currentTimeMillis();
            System.out.println("eachtime:" + (end1 - start1));

        });

        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start2));
        Thread.sleep(5000l);
        System.out.println("total time:" + (end - start));
    }
}
