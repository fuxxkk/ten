package huang.yong.chang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import huang.yong.chang.base.BaseEntity;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@TableName("tb_user")
@Data
public class User extends BaseEntity<User> implements UserDetails, Serializable {


    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("parent_id")
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
    private Boolean isDeteleted;

    @TableField(exist = false)
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.stream().forEach(role -> {
                auths.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
        }
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
