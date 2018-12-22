package huang.yong.chang.entity.request;

import huang.yong.chang.base.PageRequest;
import lombok.Data;

@Data
public class RechargePageRequest extends PageRequest {
    private Long userId;
}
