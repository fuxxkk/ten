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
 * 积分变动记录表
 */
@Data
@TableName("tb_integral_record")
public class IntegralRecord extends BaseEntity<IntegralRecord> {

    public IntegralRecord(Long userId, Double changeIntegral, Date modifyDate) {
        this.userId = userId;
        this.changeIntegral = changeIntegral;
        this.modifyDate = modifyDate;
    }

    public IntegralRecord() {
    }

    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    //变动积分
    @TableField("change_integral")
    private Double changeIntegral;

    @TableField("modify_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

}
