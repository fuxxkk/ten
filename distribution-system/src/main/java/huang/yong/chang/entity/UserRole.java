package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_user_role")
@Data
public class UserRole {

    @TableField("id")
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
