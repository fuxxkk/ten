package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 用户商品表
 */
@TableName("tb_user_item")
@Data
public class UserItem extends BaseEntity<UserItem> {

    @TableField("user_id")
    private Long userId;

    @TableField("item_id")
    private Long itemId;

    @TableField("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @TableField("status")
    private Integer status;//返佣状态：【0、返佣中 1、已完成】

    @TableField("current_day")
    private Integer currentDay; //当前生长天数
}
