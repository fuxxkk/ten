package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.*;
import huang.yong.chang.entity.request.RechargePageRequest;
import huang.yong.chang.mapper.RechargeMapper;
import huang.yong.chang.service.*;
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

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private BalanceRecordService balanceRecordService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private IntegralRecordService integralRecordService;

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

    @Override
    public Boolean setComfirmById(Long id) {
        //查询出该条充值记录的信息
        Recharge rechargeFromMsg = selectOne(id);
        //更改用户余额
        balanceService.updateUserBalance(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney());
        //todo 抽佣

        //增加余额记录
        BalanceRecord balanceRecord = new BalanceRecord(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney(), new Date());
        balanceRecordService.save(balanceRecord);
        //更改用户积分
        integralService.updateUserIntegral(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney());
        //增加积分记录
        IntegralRecord integralRecord = new IntegralRecord(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney(), new Date());
        integralRecordService.save(integralRecord);

        rechargeFromMsg.setIsConfirm(true);
        return update(rechargeFromMsg);
    }
}
