package huang.yong.chang.sysinfo.controller;

import huang.yong.chang.entity.MemBean;
import huang.yong.chang.sysinfo.service.SysInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperic.sigar.SigarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys")
@Api(tags = "系统信息")
public class SysInfoController {

    @Autowired
    private SysInfoService sysInfoService;

    @GetMapping("jvm")
    @ApiOperation(value = "获取jvm信息")
    public MemBean getJvmInfo() {
        return sysInfoService.getJvmInfo();
    }

    @GetMapping("mem")
    @ApiOperation(value = "获取内存信息")
    public MemBean getMemInfo() throws Exception {
        return sysInfoService.getMemInfo();
    }

    @GetMapping("disk")
    @ApiOperation(value = "获取硬盘信息")
    public List<MemBean> getDiskInfo() throws SigarException {
        return sysInfoService.getDiskInfo();
    }
}
