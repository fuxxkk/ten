package huang.yong.chang.controller;

import com.google.zxing.WriterException;
import huang.yong.chang.base.Result;
import huang.yong.chang.util.ContextUtils;
import huang.yong.chang.util.QrCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("test")
@RestController
public class TestController {


    @GetMapping("hello")
    public Result hello(String arg) throws Exception {
        return Result.SUCCESS("hello:"+arg);
    }

    @GetMapping("qr")
    public void testQr(HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        QrCodeUtils.createQrCode(response.getOutputStream(), "abcd", 510, "jpeg");
    }

    /*@PostMapping("login")
    public Result login(String username) {

    }*/

}
