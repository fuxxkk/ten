package huang.yong.chang.controller;

import com.google.zxing.WriterException;
import huang.yong.chang.base.Result;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;
import huang.yong.chang.service.ItemService;
import huang.yong.chang.util.QrCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequestMapping("item")
@RestController
@Api(tags = "商品管理")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("save")
    @ApiOperation(value = "添加商品")
    public Result save(@RequestBody Item item) {
        item.setCreateDate(new Date());
        return Result.SUCCESS(itemService.save(item));
    }

    @PostMapping("update")
    @ApiOperation(value = "编辑商品")
    public Result update(@RequestBody Item item) {
        item.setModifyDate(new Date());
        return Result.SUCCESS(itemService.update(item));
    }

    @GetMapping("delete")
    @ApiOperation(value = "删除商品")
    public Result delete(Long id) {
        return Result.SUCCESS(itemService.delete(id));
    }

    @GetMapping("findOne")
    @ApiOperation(value = "根据id查询商品")
    public Result findOne(Long id) {
        return Result.SUCCESS(itemService.selectOne(id));
    }

    @PostMapping("findPage")
    @ApiOperation(value = "分页查询商品")
    public Result findPage(@RequestBody ItemPageRequest itemPageRequest) {
        itemPageRequest.setPage(itemPageRequest.getPage()-1);
        return Result.SUCCESS(itemService.findPage(itemPageRequest));
    }
}
