package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.UserMsg;
import huang.yong.chang.entity.request.RechargePageRequest;
import huang.yong.chang.mapper.RechargeMapper;
import huang.yong.chang.service.RechargeService;
import huang.yong.chang.service.UserMsgService;
import huang.yong.chang.service.UserService;
import huang.yong.chang.util.ContextUtils;
import huang.yong.chang.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl extends BaseServiceImpl<Recharge, RechargeMapper> implements RechargeService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMsgService userMsgService;

    @Override
    public Boolean rechage(Recharge recharge) {
        Long id = IdUtil.getId();
        User user = ContextUtils.getUser();
        recharge.setUserId(user.getId());
        Date newDate = new Date();
        recharge.setRechargeDate(newDate);
        recharge.setId(id);

        //发送消息给管理员
        User admin = userService.findByUsername("admin");
        UserMsg userMsg = new UserMsg(admin.getId(), id, recharge.getRechargeMoney(), false, newDate);
        String content = "用户：" + user.getUsername() + "(" + user.getAlipayAccount() + "," + user.getAlipayName() + ") " +
                "在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "进行了充值，金额为："+recharge.getRechargeMoney()+"元 ，请确认。";
        userMsg.setContent(content);
        userMsgService.save(userMsg);

        return mapper.insert(recharge) > 0 ? true : false;
    }

    @Override
    public List<Recharge> findByUserId(RechargePageRequest rechargePageRequest) {
        if (rechargePageRequest.getUserId() == null) {
            rechargePageRequest.setUserId(ContextUtils.getUser().getId());
        }
        rechargePageRequest.setPage(rechargePageRequest.getPage() - 1);
        return mapper.findByUserId(rechargePageRequest);
    }
}
