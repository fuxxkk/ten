package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import huang.yong.chang.base.BaseEntity;
import huang.yong.chang.config.DoubleJsonDeserializer;
import huang.yong.chang.config.LongJsonDeserializer;
import huang.yong.chang.config.LongJsonSerializer;
import lombok.Data;

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
    @JsonSerialize(using = DoubleJsonDeserializer.class)
    private Double balance;

    @TableField("modify_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

}
