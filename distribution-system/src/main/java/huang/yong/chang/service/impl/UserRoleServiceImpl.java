package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.UserRole;
import huang.yong.chang.mapper.UserRoleMapper;
import huang.yong.chang.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {
    @Override
    public Boolean deleteByUserOrRoleId(String column, Long id) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.eq(column, id);
        int num = mapper.delete(wrapper);
        return num > 0 ? true : false;
    }
}
