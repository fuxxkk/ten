package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Result {

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private boolean flag;

    private Integer code;

    private String message;

    private Object data;  //返回数据

    public final static Result SUCCESS(Object data) {
        return new Result(true, StatusCode.OK, StatusMsg.OK, data);
    }
    public final static Result ERROR(Object data) {
        return new Result(false, StatusCode.ERROR, StatusMsg.ERROR, data);
    }
}
