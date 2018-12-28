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
 * 用户余额表
 */
@Data
@TableName("tb_balance")
public class Balance extends BaseEntity<Balance> {

    public Balance(Long userId, Double balance, Date modifyDate) {
        this.userId = userId;
        this.balance = balance;
        this.modifyDate = modifyDate;
    }

    public Balance() {
    }

    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;
    //余额
    @TableField("balance")
    private Double balance;

    @TableField("modify_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

}
