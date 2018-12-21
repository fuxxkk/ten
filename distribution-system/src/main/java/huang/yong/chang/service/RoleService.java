package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService extends BaseService<Role> {

    Boolean deleteRole(Long id);

    List<Role> findByUserId(Long userId);

    Role findByName(String roleName);
}
