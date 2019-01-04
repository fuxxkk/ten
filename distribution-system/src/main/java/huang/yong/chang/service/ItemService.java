package huang.yong.chang.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.request.ItemPageRequest;

import java.util.List;


public interface ItemService extends BaseService<Item> {

    IPage<Item> findPage(ItemPageRequest itemPageRequest);

    /**
     * 根据名字查找商品
     * @param itemName
     * @return
     */
    List<Item> findIdsByName(String itemName);
}
