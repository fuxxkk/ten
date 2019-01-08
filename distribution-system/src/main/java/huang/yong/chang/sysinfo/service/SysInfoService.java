package huang.yong.chang.sysinfo.service;

import huang.yong.chang.entity.MemBean;
import org.hyperic.sigar.SigarException;

import java.util.List;

public interface SysInfoService {

    MemBean getJvmInfo();

    MemBean getMemInfo() throws Exception;

    /**
     * 获取硬盘空间
     * @return
     */
    List<MemBean> getDiskInfo() throws SigarException;
}
