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
        User user;
        if (recharge.getUserId() == null) {
            user = ContextUtils.getUser();
        } else {
            user = userService.selectOne(recharge.getUserId());
        }
        recharge.setUserId(user.getId());
        Date newDate = new Date();
        recharge.setRechargeDate(newDate);
        recharge.setId(id);

        //发送消息给管理员
        User admin = userService.findByUsername("admin");
        UserMsg userMsg = new UserMsg(admin.getId(), id, recharge.getRechargeMoney(), false, newDate);
        String content = "用户：" + user.getUsername() + "(" + user.getAlipayAccount() + "," + user.getAlipayName() + ") " +
                "在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "进行了充值，金额为：" + recharge.getRechargeMoney() + "元 ，请确认。";
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
    public Boolean setComfirmById(Long id, Double percent) {
        //查询出该条充值记录的信息
        Recharge rechargeFromMsg = selectOne(id);
        //更改用户余额
        balanceService.updateUserBalance(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney());
        //查出父级
        User user = userService.selectOne(rechargeFromMsg.getUserId());
        Long parentId = user.getParentId();
        Date newDate = new Date();
        //如果存在父级联系人
        if (parentId != null && parentId != 0) {
            //佣金和积分
            double brokerage = rechargeFromMsg.getRechargeMoney() * percent;
            //更改佣金和积分以及记录
            balanceService.updateUserBalance(parentId, brokerage);
            integralService.updateUserIntegral(parentId, brokerage);

            BalanceRecord pbalanceRecord = new BalanceRecord(parentId, brokerage, newDate);
            balanceRecordService.save(pbalanceRecord);
            IntegralRecord pintegralRecord = new IntegralRecord(parentId, brokerage, newDate);
            integralRecordService.save(pintegralRecord);
            //发送消息
            UserMsg puserMsg = new UserMsg(parentId, null, null, false, newDate);
            String pcontent = "您的下级联系人（手机号：" + user.getPhone() + "）进行了充值，您得到了佣金："+brokerage+"元，积分："+brokerage+"分。";
            puserMsg.setContent(pcontent);
            userMsgService.save(puserMsg);
        }


        //增加余额记录
        BalanceRecord balanceRecord = new BalanceRecord(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney(), newDate);
        balanceRecordService.save(balanceRecord);
        //更改用户积分
        integralService.updateUserIntegral(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney());
        //增加积分记录
        IntegralRecord integralRecord = new IntegralRecord(rechargeFromMsg.getUserId(), rechargeFromMsg.getRechargeMoney(), newDate);
        integralRecordService.save(integralRecord);

        //给充值用户发消息
        UserMsg userMsg = new UserMsg(rechargeFromMsg.getUserId(), null, null, false, newDate);
        String content = "管理员已对你的充值，充值金额：" + rechargeFromMsg.getRechargeMoney() + " 进行了确认。";
        userMsg.setContent(content);
        userMsgService.save(userMsg);

        rechargeFromMsg.setIsConfirm(true);
        return update(rechargeFromMsg);
    }
}
