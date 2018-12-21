package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.request.UserPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    List<User> findPage(UserPageRequest userPageRequest);
}
