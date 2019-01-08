package huang.yong.chang.sysinfo.service.impl;

import huang.yong.chang.entity.MemBean;
import huang.yong.chang.sysinfo.service.SysInfoService;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.hyperic.sigar.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class SysInfoServiceImpl implements SysInfoService {
    @Override
    public MemBean getJvmInfo() {

        Runtime r = Runtime.getRuntime();
        MemBean memBean = new MemBean();
        memBean.setFreeMem((int) (r.freeMemory()/1024));
        memBean.setTotalMem((int) (r.totalMemory()/1024));
        memBean.setUsedMem((int) ((r.totalMemory()-r.freeMemory())/1024));
        memBean.setDate(new SimpleDateFormat("HH:mm:ss").format(new Date()));
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
        memBean.setDate(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        return memBean;
    }

    @Override
    public List<MemBean> getDiskInfo() throws SigarException {
        Sigar sigar = new Sigar();
        FileSystem[] fileSystemList = sigar.getFileSystemList();
        List<MemBean> memBeans = Observable.fromArray(fileSystemList).observeOn(Schedulers.io()).map(fs -> {
            FileSystemUsage usage = null;
            try {
                usage = sigar.getFileSystemUsage(fs.getDirName());
            } catch (SigarException e) {
                e.printStackTrace();
            }
            MemBean memBean = new MemBean();

            if (fs.getType() == 2) {
                memBean.setTotalMem((int) usage.getTotal());
                memBean.setFreeMem((int) usage.getFree());
                memBean.setUsedMem((int) usage.getUsed());
            }
            return memBean;
        }).toList().blockingGet();
        return memBeans;
    }
}
