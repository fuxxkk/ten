package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.UserMsg;
import huang.yong.chang.entity.request.UserMsgPageRequest;
import huang.yong.chang.mapper.UserMsgMapper;
import huang.yong.chang.service.UserMsgService;
import huang.yong.chang.util.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserMsgServiceImpl extends BaseServiceImpl<UserMsg, UserMsgMapper> implements UserMsgService {

    @Override
    public Boolean setRead(Long id) {
        QueryWrapper<UserMsg> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        UserMsg userMsg = new UserMsg();
        userMsg.setIsRead(true);
        userMsg.setReadDate(new Date());
        int update = mapper.update(userMsg, wrapper);
        return update > 0 ? true : false;
    }

    @Override
    public List<UserMsg> findByUserId(UserMsgPageRequest userMsgPageRequest) {
        if (userMsgPageRequest.getUserId() == null) {
            userMsgPageRequest.setUserId(ContextUtils.getUser().getId());
        }
        userMsgPageRequest.setPage(userMsgPageRequest.getPage() - 1);
        List<UserMsg> userMsgs = mapper.findByUserId(userMsgPageRequest);
        return userMsgs;
    }
}
