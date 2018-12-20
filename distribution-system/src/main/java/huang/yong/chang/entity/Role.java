package huang.yong.chang.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("tb_role")
@Data
public class Role {

    @TableField("id")
    private Long id;

    @TableField("role_name")
    private String roleName;

    @TableField("create_date")
    private Date createDate;

    @TableField("modify_date")
    private Date modifyDate;

    @TableField("backup1")
    private String backup1;

    @TableField("backup2")
    private String backup2;

    @TableField("backup3")
    private String backup3;
}
