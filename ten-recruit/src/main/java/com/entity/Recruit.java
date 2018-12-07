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

    @TableField("`condition`")
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

    public Recruit(String id, String jobname, String salary, String condition, String education, String type,
                   String address, String eid, Date createtime, String state, String url, String label, String content1, String content2) {
        this.id = id;
        this.jobname = jobname;
        this.salary = salary;
        this.condition = condition;
        this.education = education;
        this.type = type;
        this.address = address;
        this.eid = eid;
        this.createtime = createtime;
        this.state = state;
        this.url = url;
        this.label = label;
        this.content1 = content1;
        this.content2 = content2;
    }

    public Recruit() {
    }
}
