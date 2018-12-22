package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 充值记录实体类
 */
@Data
@TableName("tb_recharge")
public class Recharge extends BaseEntity<Recharge> {

    public Recharge(Long userId, Double rechargeMoney, Date rechargeDate) {
        this.userId = userId;
        this.rechargeMoney = rechargeMoney;
        this.rechargeDate = rechargeDate;
    }

    public Recharge() {
    }

    @TableField("user_id")
    private Long userId;

    //充值金额
    @TableField("recharge_money")
    private Double rechargeMoney;

    //充值时间
    @TableField("recharge_date")
    private Date rechargeDate;

}
