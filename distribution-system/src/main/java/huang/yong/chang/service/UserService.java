package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.User;

public interface UserService extends BaseService<User> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);
}
