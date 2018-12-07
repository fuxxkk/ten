package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_recruit")
public class Recruit {

    @TableField("id")
    private String id;

    @TableField("jobname")
    private String jobname;

    @TableField("salary")
    private String salary;

    @TableField("condition")
    private String condition;

    @TableField("education")
    private String education;

    @TableField("type")
    private String type;

    @TableField("address")
    private String address;

    @TableField("eid")
    private String eid;

    @TableField("createtime")
    private Date createtime;

    @TableField("state")
    private String state;

    @TableField("url")
    private String url;

    @TableField("label")
    private String label;

    @TableField("content1")
    private String content1;

    @TableField("content2")
    private String content2;
}
