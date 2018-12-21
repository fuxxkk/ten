package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

/**
 * 用户消息表
 */
@TableName("tb_user_msg")
@Data
public class UserMsg  extends BaseEntity<UserMsg> {


    @TableField("user_id")
    private Long userId;

    @TableField("content")
    private Long content;

    @TableField("is_read")
    private Long isRead;

    @TableField("recieve_date")
    private Long recieveDate;

    @TableField("read_date")
    private Long readDate;

}
