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

@TableName("tb_delete_user")
@Data
public class DeleteUser implements Serializable {

    public DeleteUser(Long id, String username, Long parentId, String alipayAccount, String alipayName, Date deleteDate) {
        this.id = id;
        this.username = username;
        this.parentId = parentId;
        this.alipayAccount = alipayAccount;
        this.alipayName = alipayName;
        this.deleteDate = deleteDate;
    }

    public DeleteUser() {
    }

    @TableField("id")
    private Long id;

    @TableField("username")
    private String username;

    @TableField("parent_id")
    private Long parentId;

    @TableField("alipay_account")
    private String alipayAccount;

    @TableField("alipay_name")
    private String alipayName;

    @TableField("delete_date")
    private Date deleteDate;


}
