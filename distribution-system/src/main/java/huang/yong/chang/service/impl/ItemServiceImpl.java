package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;
import huang.yong.chang.mapper.ItemMapper;
import huang.yong.chang.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, ItemMapper> implements ItemService {
    @Override
    public List<Item> findPage(ItemPageRequest itemPageRequest) {
        return mapper.findPage(itemPageRequest);
    }
}
