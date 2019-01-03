package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.UserItem;
import huang.yong.chang.excep.SystemException;

public interface UserItemService extends BaseService<UserItem> {
    /**
     * 购买商品
     * @param itemId
     * @return
     */
    Boolean buyItem(Long itemId) throws SystemException;
}
