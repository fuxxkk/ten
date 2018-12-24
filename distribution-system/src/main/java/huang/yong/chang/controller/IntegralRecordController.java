package huang.yong.chang.controller;

import huang.yong.chang.base.PageRequest;
import huang.yong.chang.base.Result;
import huang.yong.chang.entity.IntegralRecord;
import huang.yong.chang.entity.request.IntegralRecordPageRequest;
import huang.yong.chang.service.IntegralRecordService;
import huang.yong.chang.service.IntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integralRecord")
@Api(tags = "积分记录管理")
public class IntegralRecordController {

    @Autowired
    private IntegralRecordService integralRecordService;

    @PostMapping("findPage")
    @ApiOperation(value = "积分记录分页查询")
    public Result findPage(@RequestBody IntegralRecordPageRequest pageRequest) {
        return Result.SUCCESS(integralRecordService.findPageByUserId(pageRequest));
    }
}
