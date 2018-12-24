package huang.yong.chang.controller;

import huang.yong.chang.base.PageRequest;
import huang.yong.chang.base.Result;
import huang.yong.chang.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integral")
@Api(tags = "积分管理")
public class IntegralController {

    @Autowired
    private IntegralService integralService;

    @PostMapping("findPage")
    @ApiOperation(value = "积分分页查询")
    public Result findPage(@RequestBody PageRequest pageRequest) {
        return Result.SUCCESS(integralService.findPage(pageRequest));
    }

    @GetMapping("findByUserId")
    @ApiOperation(value = "查询用户积分（传参查询指定用户，不传参查询当前登录用户）")
    public Result findByUserId(@RequestParam(required = false) Long userId) {
        return Result.SUCCESS(integralService.findByUserId(userId));
    }
}
