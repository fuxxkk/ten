package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 积分变动记录表
 */
@Data
@TableName("tb_integral_record")
public class IntegralRecord extends BaseEntity<IntegralRecord> {


    @TableField("user_id")
    private Long userId;

    //变动积分
    @TableField("change_integral")
    private Double changeIntegral;

    @TableField("modify_date")
    private Date modify_date;

}
