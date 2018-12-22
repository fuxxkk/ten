package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.UserMsg;
import huang.yong.chang.entity.request.UserMsgPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMsgMapper extends BaseMapper<UserMsg> {

    List<UserMsg> findByUserId(UserMsgPageRequest userMsgPageRequest);
}
