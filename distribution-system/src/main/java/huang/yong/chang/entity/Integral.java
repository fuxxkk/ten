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

@Data
@TableName("tb_integral")
public class Integral extends BaseEntity<Integral> {

    public Integral(Long userId, Double integral, Date modifyDate) {
        this.userId = userId;
        this.integral = integral;
        this.modifyDate = modifyDate;
    }

    public Integral() {
    }

    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    //剩余积分
    @TableField("integral")
    @JsonSerialize(using = DoubleJsonDeserializer.class)
    private Double integral;

    @TableField("modify_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
}
