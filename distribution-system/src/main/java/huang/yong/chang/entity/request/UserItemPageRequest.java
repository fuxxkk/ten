package huang.yong.chang.entity.request;

import huang.yong.chang.base.PageRequest;
import lombok.Data;

@Data
public class UserItemPageRequest  extends PageRequest {

    private String itemName;

    private Long userId;
}
