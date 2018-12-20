package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户消息表
 */
@TableName("tb_msg")
@Data
public class UserMsg {


    @TableField("id")
    private Long id;

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

    @TableField("backup1")
    private String backup1;

    @TableField("backup2")
    private String backup2;

    @TableField("backup3")
    private String backup3;
}
