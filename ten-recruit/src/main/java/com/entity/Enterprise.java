package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_enterprise")
public class Enterprise {

    @TableField("id")
    private String id;

    @TableField("name")
    private String name;

    @TableField("summary")
    private String summary;

    @TableField("address")
    private String address;

    @TableField("labels")
    private String labels;

    @TableField("coordinate")
    private String coordinate;

    @TableField("ishot")
    private String ishot;

    @TableField("logo")
    private String logo;

    @TableField("jobcount")
    private Integer jobcount;

    @TableField("url")
    private String url;

    public Enterprise(String id, String name, String summary, String address, String labels, String coordinate, String ishot, String logo, Integer jobcount, String url) {
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.address = address;
        this.labels = labels;
        this.coordinate = coordinate;
        this.ishot = ishot;
        this.logo = logo;
        this.jobcount = jobcount;
        this.url = url;
    }

    public Enterprise() {
    }
}
