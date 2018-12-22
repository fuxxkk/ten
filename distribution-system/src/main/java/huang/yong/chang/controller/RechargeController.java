package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("recharge")
@RestController
@Api(tags = "充值管理")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping("rechage")
    @ApiOperation(value = "用户充值")
    public Result rechage(@RequestBody Recharge recharge)  {
        return Result.SUCCESS(rechargeService.rechage(recharge));
    }
}
