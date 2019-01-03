package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemMapper extends BaseMapper<Item> {

    List<Item> findPage(ItemPageRequest itemPageRequest);

}
