package huang.yong.chang.entity.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String alipayAccount;
    private String alipayName;

    //余额
    private Integer balance;
    //团队成员
    private List<UserDTO> users;

    //团队总余额
    private Integer totalBalance;
}
