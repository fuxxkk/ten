package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.DeleteUser;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.request.UserPageRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    List<User> findPage(UserPageRequest userPageRequest);

    Boolean saveDeleteUser(@Param("deleteUser") DeleteUser deleteUser);
}
