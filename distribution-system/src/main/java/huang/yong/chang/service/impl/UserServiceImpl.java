package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.User;
import huang.yong.chang.mapper.UserMapper;
import huang.yong.chang.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserMapper> implements UserService {
}
