package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 积分变动记录表
 */
@Data
@TableName("tb_integral_record")
public class IntegralRecord {

    @TableField("id")
    private Long id;

    @TableField("user_id")
    private Long userId;

    //变动积分
    @TableField("change_integral")
    private Integer changeIntegral;

    @TableField("modify_date")
    private Date modify_date;

    @TableField("backup1")
    private String backup1;

    @TableField("backup2")
    private String backup2;

    @TableField("backup3")
    private String backup3;

}
