package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.request.UserMsgPageRequest;
import huang.yong.chang.service.BalanceService;
import huang.yong.chang.service.UserMsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userMsg")
@Api(tags = "用户消息管理")
public class UserMsgCotroller {

    @Autowired
    private UserMsgService userMsgService;

    @GetMapping("setRead")
    @ApiOperation(value = "设置已读")
    public Result setRead(Long id) {
        return Result.SUCCESS(userMsgService.setRead(id));
    }

    @PostMapping("findByUserId")
    @ApiOperation(value = "查询当前用户消息(分页查询）")
    public Result findByUserId(@RequestBody UserMsgPageRequest userMsgPageRequest) {
        return Result.SUCCESS(userMsgService.findByUserId(userMsgPageRequest));
    }
}
