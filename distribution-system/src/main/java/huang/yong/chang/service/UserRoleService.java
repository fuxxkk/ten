package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.UserRole;

public interface UserRoleService extends BaseService<UserRole> {

    Boolean deleteByUserOrRoleId(String column, Long id);

}
