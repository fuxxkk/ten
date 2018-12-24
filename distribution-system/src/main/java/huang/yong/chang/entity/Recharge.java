package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import huang.yong.chang.base.BaseEntity;
import huang.yong.chang.config.LongJsonDeserializer;
import huang.yong.chang.config.LongJsonSerializer;
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
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    //充值金额
    @TableField("recharge_money")
    private Double rechargeMoney;

    //充值时间
    @TableField("recharge_date")
    private Date rechargeDate;

    //是否确认充值
    @TableField("is_confirm")
    private Boolean isConfirm;
}
