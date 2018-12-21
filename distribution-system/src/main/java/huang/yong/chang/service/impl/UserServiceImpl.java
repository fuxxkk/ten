package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.*;
import huang.yong.chang.entity.request.UserPageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.mapper.UserMapper;
import huang.yong.chang.service.*;
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

        balanceService.save(new Balance(user.getId(), 0, new Date()));
        integralService.save(new Integral(user.getId(), 0, new Date()));

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
        userRoleService.deleteByUserOrRoleId("user_id", id);
        return delete(id);
    }

    @Override
    public List<User> findPage(UserPageRequest userPageRequest) {
        /*Page<User> userPage = new Page<>(userPageRequest.getPage() - 1, userPageRequest.getPageSize());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (Stringu) {
        }*/
        userPageRequest.setPage(userPageRequest.getPage()-1);
        List<User> users = mapper.findPage(userPageRequest);
        return users;
    }

}
