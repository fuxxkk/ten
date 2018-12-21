package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户余额表
 */
@Data
@TableName("tb_balance")
public class Balance extends BaseEntity<Balance> {

    public Balance(Long userId, Integer balance, Date modifyDate) {
        this.userId = userId;
        this.balance = balance;
        this.modifyDate = modifyDate;
    }

    public Balance() {
    }

    @TableField("user_id")
    private Long userId;
    //余额
    @TableField("balance")
    private Integer balance;

    @TableField("modify_date")
    private Date modifyDate;

}
