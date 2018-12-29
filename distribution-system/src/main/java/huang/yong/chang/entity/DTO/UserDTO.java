package huang.yong.chang.entity.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import huang.yong.chang.config.DoubleJsonDeserializer;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String alipayAccount;
    private String alipayName;

    //余额
    @JsonSerialize(using = DoubleJsonDeserializer.class)
    private Double balance;
    //团队成员
    private List<UserDTO> users;

    //团队总余额
    @JsonSerialize(using = DoubleJsonDeserializer.class)
    private Double totalBalance;

    //团队总人数
    private Integer totalMemberCount;
}
