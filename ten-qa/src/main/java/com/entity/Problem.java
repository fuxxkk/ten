package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private String createtime;

    @TableField("updatetime")
    private String updatetime;

    @TableField("userid")
    private String userid;

    @TableField("nickname")
    private String nickname;

    @TableField("visits")
    private String visits;

    @TableField("thumbup")
    private String reply;

    @TableField("solve")
    private String solve;

    @TableField("replyname")
    private String replyname;

    @TableField("replytime")
    private String replytime;
}
