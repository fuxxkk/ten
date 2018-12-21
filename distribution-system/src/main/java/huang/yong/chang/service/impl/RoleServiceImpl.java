package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Role;
import huang.yong.chang.mapper.RoleMapper;
import huang.yong.chang.service.RoleService;
import huang.yong.chang.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleMapper> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Boolean deleteRole(Long id) {
        userRoleService.deleteByUserOrRoleId("role_id", (Long) id);

        return super.delete(id);
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return mapper.findByUserId(userId);
    }
}
