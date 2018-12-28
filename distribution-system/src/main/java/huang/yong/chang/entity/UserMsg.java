package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import huang.yong.chang.base.BaseEntity;
import huang.yong.chang.config.LongJsonDeserializer;
import huang.yong.chang.config.LongJsonSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @TableField("content")
    private String content;

    @TableField("recharge_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long rechargeId;

    @TableField("recharge_money")
    private Double rechargeMoney;

    @TableField("is_read")
    private Boolean isRead;

    @TableField("recieve_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recieveDate;

    @TableField("read_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readDate;

}
