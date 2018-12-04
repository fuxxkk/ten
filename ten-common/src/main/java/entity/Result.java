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
}
