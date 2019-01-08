package huang.yong.chang.sysinfo.controller;

import huang.yong.chang.entity.JVMBean;
import huang.yong.chang.sysinfo.service.SysInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
@Api(tags = "系统信息")
public class SysInfoController {

    @Autowired
    private SysInfoService sysInfoService;

    @GetMapping("jvm")
    @ApiOperation(value = "获取jvm信息")
    public JVMBean getJvmInfo() {
        return sysInfoService.getJvmInfo();
    }
}
