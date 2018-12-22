package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户消息表
 */
@TableName("tb_user_msg")
@Data
public class UserMsg  extends BaseEntity<UserMsg> {

    public UserMsg(Long userId, Long rechargeId, Double rechargeMoney, Boolean isRead, Date recieveDate) {
        this.userId = userId;
        this.rechargeId = rechargeId;
        this.rechargeMoney = rechargeMoney;
        this.isRead = isRead;
        this.recieveDate = recieveDate;
    }

    public UserMsg() {
    }

    @TableField("user_id")
    private Long userId;

    @TableField("content")
    private String content;

    @TableField("recharge_id")
    private Long rechargeId;

    @TableField("recharge_money")
    private Double rechargeMoney;

    @TableField("is_read")
    private Boolean isRead;

    @TableField("recieve_date")
    private Date recieveDate;

    @TableField("read_date")
    private Date readDate;

}
