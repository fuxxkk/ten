package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.DTO.UserDTO;
import huang.yong.chang.entity.Role;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.request.UserPageRequest;
import huang.yong.chang.entity.request.UserVO;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.service.UserService;
import huang.yong.chang.util.ContextUtils;
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

    @GetMapping("findUserTeamatesDetailByUserId")
    @ApiOperation(value = "查找团队成员")
    public Result findUserTeamatesDetailByUserId(Long id) {
        return Result.SUCCESS(userService.findUserTeamatesDetailByUserId(id));
    }
    @GetMapping("user")
    @ApiOperation(value = "获取当前登录用户信息")
    public Result getUser() {
        return Result.SUCCESS(ContextUtils.getUser());
    }

    @PostMapping("checkUser")
    @ApiOperation(value = "验证用户")
    public Result checkUser(@RequestBody UserVO userVO) {
        return Result.SUCCESS(userService.checkUser(userVO));
    }

    @PostMapping("chagePwdNoLogin")
    @ApiOperation(value = "修改密码（未登录）")
    public Result chagePwdNoLogin(@RequestBody UserVO userVO) throws SystemException {
        return Result.SUCCESS(userService.chagePwd(userVO));
    }

    @PostMapping("chagePwdLogin")
    @ApiOperation(value = "修改密码（已登录）")
    public Result chagePwdLogin(@RequestBody UserVO userVO) throws SystemException {
        return Result.SUCCESS(userService.chagePwd(userVO));
    }

    @GetMapping("isLogin")
    @ApiOperation(value = "是否登录")
    public Result isLogin() {
        return Result.SUCCESS(userService.isLogin());
    }

    @PostMapping("login")
    @ApiOperation(value = "登录")
    public Result login(@RequestBody User user) throws SystemException {
        return Result.SUCCESS(userService.login(user));
    }
}
