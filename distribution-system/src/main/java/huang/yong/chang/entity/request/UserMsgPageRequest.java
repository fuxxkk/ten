package huang.yong.chang.entity.request;

import huang.yong.chang.base.PageRequest;
import lombok.Data;

@Data
public class UserMsgPageRequest extends PageRequest {
    private Long userId;
}
