package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.DTO.UserItemDTO;
import huang.yong.chang.entity.UserItem;
import huang.yong.chang.entity.request.UserItemPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserItemMapper extends BaseMapper<UserItem> {
    List<UserItem> findPage(UserItemPageRequest userItemPageRequest);
}
