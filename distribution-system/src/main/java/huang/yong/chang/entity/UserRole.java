package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

@TableName("tb_user_role")
@Data
public class UserRole extends BaseEntity<UserRole> {

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRole() {
    }

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
