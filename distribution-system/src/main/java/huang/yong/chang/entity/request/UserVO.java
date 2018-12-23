package huang.yong.chang.entity.request;

import lombok.Data;

@Data
public class UserVO  {
    private String username;

    private String newPassword;

    private String oldPassword;

    private String alipayName;

    private String alipayAccount;

    private String phone;
}
