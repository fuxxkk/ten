package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Role;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.request.UserPageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("save")
    @ApiOperation(value = "增加用户")
    public Result save(@RequestBody User user) throws SystemException {
        return Result.SUCCESS(userService.saveUser(user));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新用户")
    public Result update(@RequestBody User user) throws SystemException {
        return Result.SUCCESS(userService.updateUser(user));
    }

    @GetMapping("delete")
    @ApiOperation(value = "删除用户")
    public Result delete(Long id) {
        return Result.SUCCESS(userService.deleteUser(id));
    }

    @PostMapping("findPage")
    @ApiOperation(value = "分页查询")
    public Result findPage(@RequestBody UserPageRequest userPageRequest) throws SystemException {
        return Result.SUCCESS(userService.findPage(userPageRequest));
    }
}
