package huang.yong.chang.service;


import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;

import java.util.List;

public interface ItemService extends BaseService<Item> {

    List<Item> findPage(ItemPageRequest itemPageRequest);

}
