package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_integral")
public class Integral extends BaseEntity<Integral> {

    public Integral(Long userId, Integer integral, Date modifyDate) {
        this.userId = userId;
        this.integral = integral;
        this.modifyDate = modifyDate;
    }

    public Integral() {
    }

    @TableField("user_id")
    private Long userId;

    //剩余积分
    @TableField("integral")
    private Integer integral;

    @TableField("modify_date")
    private Date modifyDate;
}
