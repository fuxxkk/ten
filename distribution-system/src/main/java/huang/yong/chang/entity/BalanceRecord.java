package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_balance_record")
public class BalanceRecord extends BaseEntity<BalanceRecord> {

    @TableField("user_id")
    private Long userId;

    //余额变动
    @TableField("change_balance")
    private Double changeBalance;

    @TableField("modify_date")
    private Date modifyDate;
}
