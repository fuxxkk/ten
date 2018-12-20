package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tb_article")
public class Article implements Serializable {
    public Article(String id, String columnid, String userid, String title, String content, String image, Date createtime, Date updatetime, Integer ispublic, Integer istop,
                   Integer visits, Integer thumbup, Integer comment, Integer state, String channelid, String url, String type) {
        this.id = id;
        this.columnid = columnid;
        this.userid = userid;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.ispublic = ispublic;
        this.istop = istop;
        this.visits = visits;
        this.thumbup = thumbup;
        this.comment = comment;
        this.state = state;
        this.channelid = channelid;
        this.url = url;
        this.type = type;
    }

    public Article() {
    }

    @TableField("id")
    private String id;

    @TableField("columnid")
    private String columnid;

    @TableField("userid")
    private String userid;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("image")
    private String image;

    @TableField("createtime")
    private Date createtime;

    @TableField("updatetime")
    private Date updatetime;

    @TableField("ispublic")
    private Integer ispublic;

    @TableField("istop")
    private Integer istop;

    @TableField("visits")
    private Integer visits;

    @TableField("thumbup")
    private Integer thumbup;

    @TableField("comment")
    private Integer comment;

    @TableField("state")
    private Integer state;

    @TableField("channelid")
    private String channelid;

    @TableField("url")
    private String url;

    @TableField("type")
    private String type;

}
