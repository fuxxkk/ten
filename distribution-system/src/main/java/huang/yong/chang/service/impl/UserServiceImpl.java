package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.*;
import huang.yong.chang.entity.DTO.UserDTO;
import huang.yong.chang.entity.request.UserPageRequest;
import huang.yong.chang.entity.request.UserVO;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.mapper.UserMapper;
import huang.yong.chang.service.*;
import huang.yong.chang.util.ContextUtils;
import huang.yong.chang.util.IdUtil;
import huang.yong.chang.util.MD5Util;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private BalanceService balanceService;

    private final ThreadLocal<Boolean> threadLocal = new ThreadLocal<>();

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return mapper.selectOne(wrapper);
    }

    @Override
    public User findByNameNqId(String username, Long id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.ne("id", id);
        return mapper.selectOne(wrapper);
    }

    @Override
    public Boolean saveUser(User user) throws SystemException {

        User byUsername = findByUsername(user.getUsername());
        if (byUsername != null) {
            throw new SystemException("用户名已重复！");
        }

        user.setCreateDate(new Date());
        user.setPassword(MD5Util.encode(user.getPassword()));
        user.setId(IdUtil.getId());
        //保存中间表
        List<Role> roles = user.getRoles();

        if (CollectionUtils.isEmpty(roles)) {
            roles = new ArrayList<>();
            roles.add(roleService.findByName("user"));
        }

        Observable.fromIterable(roles).observeOn(Schedulers.single()).forEach(role -> {
            userRoleService.save(new UserRole(user.getId(), role.getId()));
        });

        balanceService.save(new Balance(user.getId(), new Double(0), new Date()));
        integralService.save(new Integral(user.getId(), new Double(0), new Date()));

        return mapper.insert(user) > 0 ? true : false;
    }

    @Override
    public Boolean updateUser(User user) throws SystemException {
        User byNameNqId = findByNameNqId(user.getUsername(), user.getId());
        if (byNameNqId != null) {
            throw new SystemException("用户名已重复！");
        }
        user.setModifyDate(new Date());

        return super.update(user);
    }

    @Override
    public Boolean deleteUser(Long id) {
        User user = selectOne(id);
        DeleteUser deleteUser = new DeleteUser(user.getId(), user.getUsername(), user.getParentId(), user.getAlipayAccount(), user.getAlipayName(), new Date());
        mapper.saveDeleteUser(deleteUser);
        userRoleService.deleteByUserOrRoleId("user_id", id);
        return delete(id);
    }

    @Override
    public List<User> findPage(UserPageRequest userPageRequest) {
        /*Page<User> userPage = new Page<>(userPageRequest.getPage() - 1, userPageRequest.getPageSize());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (Stringu) {
        }*/
        userPageRequest.setPage(userPageRequest.getPage() - 1);
        List<User> users = mapper.findPage(userPageRequest);
        return users;
    }

    @Override
    public UserDTO findUserTeamatesDetailByUserId(Long userId) {
        User user = selectOne(userId);
        Double balance = balanceService.findBalanceByUserId(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setAlipayAccount(user.getAlipayAccount());
        userDTO.setAlipayName(user.getAlipayName());
        userDTO.setBalance(balance);
        List<User> sons = findSonByUserId(userId);
        if (CollectionUtils.isNotEmpty(sons)) {

            Iterable<UserDTO> userDTOS = Observable.fromIterable(sons).observeOn(Schedulers.io()).map(son -> {
                return findUserTeamatesDetailByUserId(son.getId());
            }).blockingIterable();
            ArrayList<UserDTO> dtos = Lists.newArrayList(userDTOS);
            userDTO.setUsers(dtos);
        }
        Double totalBalance = getTotalBalance(userDTO, 0);
        userDTO.setTotalBalance(totalBalance);
        return userDTO;
    }

    @Override
    public List<User> findSonByUserId(Long parentId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        return mapper.selectList(wrapper);
    }

    @Override
    public Boolean checkUser(UserVO userVO) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userVO.getUsername()).eq("alipay_account", userVO.getAlipayAccount())
                .eq("alipay_name", userVO.getAlipayName()).eq("phone", userVO.getPhone());
        boolean isTrue = mapper.selectOne(wrapper) == null;
        if (isTrue) {
            threadLocal.set(true);
        }
        return isTrue;
    }

    @Override
    public Boolean chagePwd(UserVO userVO) throws SystemException {
        User user = ContextUtils.getUser();
        boolean isLogin = user != null ? true : false;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String encodeNewPwd = MD5Util.encode(userVO.getNewPassword());
        if (isLogin) {//已登录
            String encodeOldPwd = MD5Util.encode(userVO.getOldPassword());
            if (user.getPassword().equalsIgnoreCase(encodeOldPwd)) {
                wrapper.eq("id", user.getId());
                User newUser = new User();
                newUser.setPassword(encodeNewPwd);
                return mapper.update(newUser, wrapper) > 0 ? true : false;
            } else {
                throw new SystemException("输入的旧密码不正确！");
            }
        } else {//未登录
            Boolean isCheck = threadLocal.get();
            if (isCheck != null && isCheck == true) {
                wrapper.eq("username", userVO.getUsername());
                User newUser = new User();
                newUser.setPassword(encodeNewPwd);
                return mapper.update(newUser, wrapper) > 0 ? true : false;
            }else {
                throw new SystemException("请先验证用户信息！");
            }
        }
    }


    private Double getTotalBalance(UserDTO userDTO, double sum) {
        sum += userDTO.getBalance();
        List<UserDTO> users = userDTO.getUsers();
        if (CollectionUtils.isNotEmpty(users)) {
            for (UserDTO user : users) {
                sum = getTotalBalance(user, sum);
            }
        }
        return sum;
    }
}
