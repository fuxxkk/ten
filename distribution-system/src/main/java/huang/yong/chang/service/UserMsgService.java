package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.UserMsg;
import huang.yong.chang.entity.request.UserMsgPageRequest;

import java.util.List;

public interface UserMsgService extends BaseService<UserMsg> {

    /**
     * 设置成已读
     * @param id
     * @return
     */
    Boolean setRead(Long id);

    /**
     * 查询当前用户消息(分页查询）
     * @param userMsgPageRequest
     * @return
     */
    List<UserMsg> findByUserId(UserMsgPageRequest userMsgPageRequest);

}
