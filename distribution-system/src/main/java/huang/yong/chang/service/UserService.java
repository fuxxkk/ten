package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.base.PageRequest;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.entity.DTO.UserDTO;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.request.UserPageRequest;
import huang.yong.chang.entity.request.UserVO;
import huang.yong.chang.excep.SystemException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService extends BaseService<User> {

    /**
     * 登录
     * @param user
     * @return
     */
    Boolean login(User user) throws SystemException;

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

    /**
     * 验证用户
     * @param userVO
     * @return
     */
    Boolean checkUser(UserVO userVO);

    /**
     * 修改密码
     * @param userVO
     * @return
     */
    Boolean chagePwd(UserVO userVO) throws SystemException;


    /**
     * 是否登录
     * @return
     */
    Boolean isLogin();

    /**
     * 退出登录
     * @return
     */
    Boolean logout();


    void getQr(HttpServletResponse response) throws  Exception;

    /**
     * 管理员登录
     * @param user
     * @return
     */
    Boolean adminLogin(User user) throws SystemException;

    /**
     * 获取管理员微信信息
     * @return
     */
    Map<String, String> adminWechat();
}
