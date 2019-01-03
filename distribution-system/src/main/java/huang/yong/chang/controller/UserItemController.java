package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.service.ItemService;
import huang.yong.chang.service.UserItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("useritem")
@RestController
@Api(tags = "用户购买商品管理")
public class UserItemController {

    @Autowired
    private UserItemService userItemService;

    @GetMapping("buyItem")
    @ApiOperation(value = "购买商品")
    public Result buyItem(Long itemId) throws SystemException {
        return Result.SUCCESS(userItemService.buyItem(itemId));
    }

    /* @PostMapping("findPage")
    @ApiOperation(value = "分页查询商品")
    public Result findPage(@RequestBody ItemPageRequest itemPageRequest) {
        itemPageRequest.setPage(itemPageRequest.getPage()-1);
        return Result.SUCCESS(itemService.findPage(itemPageRequest));
    }*/
}
