package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.request.BalanceRecordPageRequest;
import huang.yong.chang.entity.request.IntegralRecordPageRequest;
import huang.yong.chang.service.BalanceRecordService;
import huang.yong.chang.service.IntegralRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balanceRecord")
@Api(tags = "余额变动记录管理")
public class BalanceRecordController {

    @Autowired
    private BalanceRecordService balanceRecordService;

    @PostMapping("findPage")
    @ApiOperation(value = "余额变动记录分页查询")
    public Result findPage(@RequestBody BalanceRecordPageRequest pageRequest) {
        return Result.SUCCESS(balanceRecordService.findByUserId(pageRequest));
    }
}
