package huang.yong.chang.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName("tb_role")
@Data
public class Role extends BaseEntity<Role> {


    @TableField("role_name")
    private String roleName;

    @TableField("create_date")
    private Date createDate;

    @TableField("modify_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

}
