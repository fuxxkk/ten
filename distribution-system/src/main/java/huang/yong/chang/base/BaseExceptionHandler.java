package huang.yong.chang.base;

import huang.yong.chang.excep.SystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result error(SystemException e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage(),null);
    }
}
