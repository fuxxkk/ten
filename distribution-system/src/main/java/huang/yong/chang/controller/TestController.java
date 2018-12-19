package huang.yong.chang.controller;

import huang.yong.chang.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping("hello")
    public Result hello(String arg) throws Exception {
        return Result.SUCCESS("hello:"+arg);
    }
}
