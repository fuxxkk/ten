package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import huang.yong.chang.util.QrCodeUtils;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private BalanceService balanceService;

    private final Map<String, Boolean> map = Maps.newHashMap();

    @Override
    public Boolean login(User user) throws SystemException {
        if (user.getUsername().equals("admin")) {
            throw new SystemException("该用户无权限登陆");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("username", user.getUsername()).eq("password", MD5Util.encode(user.getPassword()));
        User one = mapper.selectOne(wrapper);
        if (one == null) {
            throw new SystemException("用户名或密码错误！");
        }
        List<Role> roles = roleService.findByUserId(one.getId());
        one.setRoles(roles);
        ContextUtils.setUser(one);
        return true;
    }

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

        if (StringUtils.isNotEmpty(user.getUsername()) && user.getId() != null) {
            User byNameNqId = findByNameNqId(user.getUsername(), user.getId());
            if (byNameNqId != null) {
                throw new SystemException("用户名已重复！");
            }
        }
        User loginUser = ContextUtils.getUser();

        if (user.getId() == null) {
            Optional.ofNullable(loginUser).orElseThrow(() -> new SystemException("请登录后操作"));
            user.setId(loginUser.getId());
        }

        user.setModifyDate(new Date());

        Boolean update = super.update(user);
        //如果是当前用户，就更新session
        if (update && user.getId().equals(loginUser.getId()) ) {
            ContextUtils.setUser(selectOne(user.getId()));
        }
        return update;
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
    public IPage<User> findPage(UserPageRequest userPageRequest) {
        Page<User> userPage = new Page<>(userPageRequest.getPage() - 1, userPageRequest.getPageSize());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(userPageRequest.getAlipayAccount())) {
            userQueryWrapper.like("alipay_account", userPageRequest.getAlipayAccount());
        }
        if (StringUtils.isNotEmpty(userPageRequest.getAlipayName())) {
            userQueryWrapper.like("alipay_name", userPageRequest.getAlipayName());
        }
        if (StringUtils.isNotEmpty(userPageRequest.getUsername())) {
            userQueryWrapper.like("username", userPageRequest.getUsername());
        }
        if (userPageRequest.getIsAsc() != null && StringUtils.isNotEmpty(userPageRequest.getOrderByColumn())) {
            userQueryWrapper.orderBy(true, userPageRequest.getIsAsc(), userPageRequest.getOrderByColumn());
        }
        return mapper.selectPage(userPage, userQueryWrapper);
    }

    @Override
    public UserDTO findUserTeamatesDetailByUserId(Long userId) {
        User user = selectOne(userId);
        Double balance = balanceService.findBalanceByUserId(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setAlipayAccount(user.getAlipayAccount());
        userDTO.setCreateDate(user.getCreateDate());
        userDTO.setAlipayName(user.getUsername());
        userDTO.setBalance(balance);
        List<User> sons = findSonByUserId(userId);
        if (CollectionUtils.isNotEmpty(sons)) {

            List<UserDTO> dtos = Observable.fromIterable(sons).observeOn(Schedulers.io())
                    .map(son -> findUserTeamatesDetailByUserId(son.getId()))
                    .toList().blockingGet();
            userDTO.setUsers(dtos);
        }
        Double totalBalance = getTotalBalance(userDTO, 0);
        userDTO.setTotalBalance(totalBalance);
        Integer totalMemberCount = getTotalMemberCount(userDTO, 1);
        userDTO.setTotalMemberCount(totalMemberCount);
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
        boolean isTrue = mapper.selectOne(wrapper) != null;
        map.put(userVO.getUsername(), isTrue);
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
            Boolean isCheck = map.get(userVO.getUsername());
            if (isCheck != null && isCheck == true) {
                wrapper.eq("username", userVO.getUsername());
                User newUser = new User();
                newUser.setPassword(encodeNewPwd);
                return mapper.update(newUser, wrapper) > 0 ? true : false;
            } else {
                throw new SystemException("请先验证用户信息！");
            }
        }
    }

    @Override
    public Boolean isLogin() {
        return ContextUtils.getUser() == null ? false : true;
    }

    @Override
    public Boolean logout() {
        ContextUtils.getSession().removeAttribute(ContextUtils.SESSIONNAME);
        return true;
    }

    @Override
    public void getQr(HttpServletResponse response) throws Exception {
        User user = ContextUtils.getUser();
        Optional.ofNullable(user).orElseThrow(() -> new SystemException("请先登录！"));
        QrCodeUtils.createQrCode(response.getOutputStream(), "http://156.233.65.171/register.html?pid=" + user.getId(), 900, "JPEG");
    }

    @Override
    public Boolean adminLogin(User user) throws SystemException {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("username", user.getUsername()).eq("password", MD5Util.encode(user.getPassword()));
        User one = mapper.selectOne(wrapper);
        if (one == null) {
            throw new SystemException("用户名或密码错误！");
        }
        List<Role> roles = roleService.findByUserId(one.getId());
        boolean flag = true;
        for (Role role : roles) {
            if (role.getId() == 1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            throw new SystemException("该用户不是管理员！");
        }
        one.setRoles(roles);
        ContextUtils.setUser(one);
        return true;
    }

    @Override
    public Map<String, String> adminWechat() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", "admin");
        User user = mapper.selectOne(userQueryWrapper);
        HashMap<String, String> map = Maps.newHashMap();
        map.put("wechatName", user.getBackup1());
        map.put("wechatQrUrl", user.getBackup2());
        return map;
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

    /**
     * 获取当前用户团队总人数
     *
     * @param userDTO
     * @param sum
     * @return
     */
    private Integer getTotalMemberCount(UserDTO userDTO, int sum) {
        int count;
        List<UserDTO> users = userDTO.getUsers();
        if (users == null) {
            count = 0;
        } else {
            count = users.size();
        }

        sum += count;
        if (CollectionUtils.isNotEmpty(users)) {
            for (UserDTO user : users) {
                sum = getTotalMemberCount(user, sum);
            }
        }
        return sum;
    }
}
