package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 充值记录实体类
 */
@Data
@TableName("tb_recharge")
public class Recharge {

    @TableField("id")
    private Long id;

    @TableField("user_id")
    private Long userId;

    //充值金额
    @TableField("recharge_money")
    private Integer rechargeMoney;

    //充值时间
    @TableField("recharge_date")
    private Date rechargeDate;

    @TableField("backup1")
    private String backup1;

    @TableField("backup2")
    private String backup2;

    @TableField("backup3")
    private String backup3;
}
