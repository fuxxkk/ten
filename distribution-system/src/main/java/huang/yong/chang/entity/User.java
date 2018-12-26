package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import huang.yong.chang.base.BaseEntity;
import huang.yong.chang.config.LongJsonDeserializer;
import huang.yong.chang.config.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("tb_user")
@Data
public class User extends BaseEntity<User> implements  Serializable {


    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("parent_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long parentId;

    @TableField("phone")
    private String phone;

    @TableField("alipay_account")
    private String alipayAccount;

    @TableField("alipay_name")
    private String alipayName;

    @TableField("create_date")
    private Date createDate;

    @TableField("modify_date")
    private Date modifyDate;

    @TableField("uesr_level")
    private Integer uesrLevel;

    @TableField("is_deleted")
    @ApiModelProperty(hidden = true)
    private Boolean isDeteleted;

    @TableField(exist = false)
    private List<Role> roles;


}
