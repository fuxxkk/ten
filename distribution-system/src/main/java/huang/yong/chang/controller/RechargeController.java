package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.request.RechargePageRequest;
import huang.yong.chang.entity.request.UserMsgPageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.service.RechargeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("recharge")
@RestController
@Api(tags = "充值管理")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping("rechage")
    @ApiOperation(value = "用户充值或提现（正数为充值，负数为提现）")
    public Result rechage(@RequestBody Recharge recharge) throws SystemException {
        return Result.SUCCESS(rechargeService.rechage(recharge));
    }

    @PostMapping("findByUserId")
    @ApiOperation(value = "查询当前用户充值记录(分页查询）")
    public Result findByUserId(@RequestBody RechargePageRequest rechargePageRequest) {
        return Result.SUCCESS(rechargeService.findByUserId(rechargePageRequest));
    }

    @GetMapping("setConfirm")
    @ApiOperation(value = "确认充值或提现，第一个参数是充值或提现ID，第二个参数是佣金比例，默认20%（提现时可以不填，填了无效）")
    public Result setConfirm(Long rechargeId,Double money) throws SystemException {
        return Result.SUCCESS(rechargeService.setComfirmById(rechargeId,null,money));
    }
}
