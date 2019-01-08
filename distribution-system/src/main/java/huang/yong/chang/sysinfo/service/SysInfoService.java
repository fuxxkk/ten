package huang.yong.chang.sysinfo.service;

import huang.yong.chang.entity.MemBean;
import org.hyperic.sigar.SigarException;

public interface SysInfoService {

    MemBean getJvmInfo();

    MemBean getMemInfo() throws Exception;
}
