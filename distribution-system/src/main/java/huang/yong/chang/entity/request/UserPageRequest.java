package huang.yong.chang.entity.request;

import huang.yong.chang.base.PageRequest;
import lombok.Data;

@Data
public class UserPageRequest extends PageRequest {
    private String alipayAccount;

    private String alipayName;

    private String username;
}
