package huang.yong.chang.entity.DTO;

import huang.yong.chang.entity.Item;
import lombok.Data;

import java.util.Date;

@Data
public class UserItemDTO {

    private Item item;

    private Date buyDate;

    private String progress;

    private Integer status;
}
