package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_problem")
public class Problem {

    @TableField("id")
    private String id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("createtime")
    private Date createtime;

    @TableField("updatetime")
    private Date updatetime;

    @TableField("userid")
    private String userid;

    @TableField("nickname")
    private String nickname;

    @TableField("visits")
    private Integer visits;

    @TableField("thumbup")
    private Integer reply;

    @TableField("solve")
    private String solve;

    @TableField("replyname")
    private String replyname;

    @TableField("replytime")
    private Date replytime;

    public Problem(String id, String title, String content, Date createtime, Date updatetime, String userid,
                   String nickname, Integer visits, Integer reply, String solve, String replyname, Date replytime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.reply = reply;
        this.solve = solve;
        this.replyname = replyname;
        this.replytime = replytime;
    }

    public Problem() {
    }
}
