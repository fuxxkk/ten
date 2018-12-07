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

    @TableField("id")
    private String url;

}
