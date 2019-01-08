package huang.yong.chang.sysinfo.service.impl;

import huang.yong.chang.entity.MemBean;
import huang.yong.chang.sysinfo.service.SysInfoService;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Service;


@Service
public class SysInfoServiceImpl implements SysInfoService {
    @Override
    public MemBean getJvmInfo() {

        Runtime r = Runtime.getRuntime();
        MemBean memBean = new MemBean();
        memBean.setFreeMem((int) (r.freeMemory()/1024));
        memBean.setTotalMem((int) (r.totalMemory()/1024));
        memBean.setUsedMem((int) ((r.totalMemory()-r.freeMemory())/1024));
        return memBean;
    }

    @Override
    public MemBean getMemInfo() throws Exception {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem(); //获取内存对象
        MemBean memBean = new MemBean();
        memBean.setFreeMem((int) (mem.getFree()/1024));
        memBean.setTotalMem((int) (mem.getTotal()/1024));
        memBean.setUsedMem((int) ((mem.getUsed())/1024));
        return memBean;
    }
}
