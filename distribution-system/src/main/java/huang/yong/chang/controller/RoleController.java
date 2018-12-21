package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Role;
import huang.yong.chang.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色管理")
@RequestMapping("role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("save")
    @ApiOperation(value = "增加角色")
    public Result save(@RequestBody Role role) {
        return Result.SUCCESS(roleService.save(role));
    }

    @PostMapping("update")
    @ApiOperation(value = "更新角色")
    public Result update(@RequestBody Role role) {
        return Result.SUCCESS(roleService.update(role));
    }

    @GetMapping("delete")
    @ApiOperation(value = "删除角色")
    public Result delete(Long id) {
        return Result.SUCCESS(roleService.deleteRole(id));
    }
}
