package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_pl")
public class ProblemLabel {

    @TableField("id")
    private String id;

    @TableField("problemid")
    private String problemId;

    @TableField("labelid")
    private String labelId;

    public ProblemLabel(String id, String problemId, String labelId) {
        this.id = id;
        this.problemId = problemId;
        this.labelId = labelId;
    }

    public ProblemLabel() {
    }
}
