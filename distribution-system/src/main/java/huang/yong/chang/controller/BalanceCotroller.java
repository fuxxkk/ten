package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.service.BalanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("balance")
@Api(tags = "用户余额管理")
public class BalanceCotroller {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("findBalanceByUserId")
    @ApiOperation(value = "查询当前用户余额")
    public Result findBalanceByUserId(Long id) {
        return Result.SUCCESS(balanceService.findBalanceByUserId(id));
    }
}
