package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import huang.yong.chang.base.BaseEntity;
import huang.yong.chang.config.DoubleJsonDeserializer;
import lombok.Data;

import java.util.Date;

/**
 * 商品表
 */
@TableName("tb_item")
@Data
public class Item extends BaseEntity<Item> {

    @TableField("name")
    private String name;

    @TableField("price")
    @JsonSerialize(using = DoubleJsonDeserializer.class)
    private Double price;

    @TableField("content")
    private String content;

    @TableField("pic_url")
    private String picUrl;

    @TableField("status")
    private String status; //是否下架【0、否 1、是】

    @TableField("type")
    private Integer type; //类型【0、分期 1、一次性】

    @TableField("yield")
    private Integer yield; //产量（单位：件/天）

    @TableField("cycle")
    private Integer cycle; //生长周期

    @TableField("create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @TableField("modify_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
}
