package main.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("tb_label")
public class Label extends Model<Label> {

    @TableField("id")
    private String id;//

    @TableField("labelname")
    private String labelname;//标签名称

    @TableField("state")
    private String state;//状态

    @TableField("count")
    private Integer count;//使用数量

    @TableField("fans")
    private Integer fans;//关注数

    @TableField("recommend")
    private String recommend;//是否推荐

    public Label(String id, String labelname, String state, Integer count, Integer fans, String recommend) {
        this.id = id;
        this.labelname = labelname;
        this.state = state;
        this.count = count;
        this.fans = fans;
        this.recommend = recommend;
    }
}
