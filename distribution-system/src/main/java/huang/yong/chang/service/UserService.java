package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.User;
import huang.yong.chang.excep.SystemException;

public interface UserService extends BaseService<User> {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 根据名字查询 不在此ID
     * @param name
     * @return
     */
    User findByNameNqId(String name,Long id);


    Boolean saveUser(User user) throws SystemException;

    Boolean updateUser(User user) throws SystemException;

    Boolean deleteUser(Long id);
}
