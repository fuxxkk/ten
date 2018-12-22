package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.base.PageRequest;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.entity.DTO.UserDTO;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.request.UserPageRequest;
import huang.yong.chang.excep.SystemException;

import java.util.List;

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

    List<User> findPage(UserPageRequest userPageRequest);

    /**
     * 查看当前用户的
     * @param userId
     * @return
     */
    UserDTO findUserTeamatesDetailByUserId(Long userId);

    List<User> findSonByUserId(Long parentId);

}
