package huang.yong.chang.sysinfo.service.impl;

import huang.yong.chang.entity.JVMBean;
import huang.yong.chang.sysinfo.service.SysInfoService;
import org.springframework.stereotype.Service;


@Service
public class SysInfoServiceImpl implements SysInfoService {
    @Override
    public JVMBean getJvmInfo() {

        Runtime r = Runtime.getRuntime();
        JVMBean jvmBean = new JVMBean();
        jvmBean.setFreeMem((int) (r.freeMemory()/1024));
        jvmBean.setTotalMem((int) (r.totalMemory()/1024));
        jvmBean.setUsedMem((int) ((r.totalMemory()-r.freeMemory())/1024));
        return jvmBean;
    }
}
