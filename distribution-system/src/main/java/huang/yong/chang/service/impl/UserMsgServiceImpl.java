package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.BalanceRecord;
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
    public IPage<UserMsg> findByUserId(UserMsgPageRequest userMsgPageRequest) {
        if (userMsgPageRequest.getUserId() == null) {
            userMsgPageRequest.setUserId(ContextUtils.getUser().getId());
        }

        Page<UserMsg> userMsgPage = new Page<>(userMsgPageRequest.getPage(), userMsgPageRequest.getPageSize());
        QueryWrapper<UserMsg> userMsgQueryWrapper = new QueryWrapper<>();
        userMsgQueryWrapper.eq("user_id", userMsgPageRequest.getUserId());
        if (userMsgPageRequest.getIsAsc() != null && StringUtils.isNotEmpty(userMsgPageRequest.getOrderByColumn()) ) {
            userMsgQueryWrapper.orderBy(true, userMsgPageRequest.getIsAsc(), userMsgPageRequest.getOrderByColumn());
        }
        return mapper.selectPage(userMsgPage,userMsgQueryWrapper);
    }
}
