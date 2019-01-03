package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.DTO.UserItemDTO;
import huang.yong.chang.entity.UserItem;
import huang.yong.chang.entity.request.UserItemPageRequest;
import huang.yong.chang.excep.SystemException;

import java.util.List;

public interface UserItemService extends BaseService<UserItem> {
    /**
     * 购买商品
     * @param itemId
     * @return
     */
    Boolean buyItem(Long itemId) throws SystemException;

    /**
     * 分页查询
     * @param userItemPageRequest
     * @return
     */
    List<UserItemDTO> findPage(UserItemPageRequest userItemPageRequest);
}
