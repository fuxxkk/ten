package huang.yong.chang.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName("tb_role")
@Data
public class Role extends BaseEntity<Role> {


    @TableField("role_name")
    private String roleName;

    @TableField("create_date")
    private Date createDate;

    @TableField("modify_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

}
