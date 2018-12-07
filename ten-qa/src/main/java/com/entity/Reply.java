package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_reply")
public class Reply {

    @TableField("id")
    private String id;

    @TableField("problemid")
    private String problemId;

    @TableField("content")
    private String content;

    @TableField("createtime")
    private String createTime;

    @TableField("updatetime")
    private String updateTime;

    @TableField("userid")
    private String userId;

    @TableField("nickname")
    private String nickname;

}
