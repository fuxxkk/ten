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

@Data
@TableName("tb_balance_record")
public class BalanceRecord extends BaseEntity<BalanceRecord> {

    public BalanceRecord(Long userId, Double changeBalance, Date modifyDate) {
        this.userId = userId;
        this.changeBalance = changeBalance;
        this.modifyDate = modifyDate;
    }

    public BalanceRecord() {
    }

    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    //余额变动
    @TableField("change_balance")
    private Double changeBalance;

    @TableField("modify_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
}
