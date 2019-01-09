package huang.yong.chang.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import huang.yong.chang.entity.Item;
import lombok.Data;

import java.util.Date;

@Data
public class UserItemDTO {

    private String userName;

    private Item item;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buyDate;

    private String progress;

    private Integer status;
}
