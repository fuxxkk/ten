package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.base.UserMsgContent;
import huang.yong.chang.entity.*;
import huang.yong.chang.entity.request.RechargePageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.mapper.RechargeMapper;
import huang.yong.chang.service.*;
import huang.yong.chang.util.ContextUtils;
import huang.yong.chang.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
    public Boolean rechage(Recharge recharge) throws SystemException {
        Long id = IdUtil.getId();
        DecimalFormat df = new DecimalFormat("######0.00");
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
        Double rechargeMoney = recharge.getRechargeMoney();
        String rechargeMoneyFormat = df.format(rechargeMoney);
        String dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(newDate);

        UserMsg userMsg = new UserMsg(admin.getId(), id, rechargeMoney, false, newDate);
        String content;
        if (rechargeMoney > 0) {
            content = String.format(UserMsgContent.RECHARGEFORADMIN, user.getUsername(), user.getPhone(), user.getAlipayName(), user.getAlipayName(), dateFormat, rechargeMoneyFormat);
        } else if (rechargeMoney == 0) {
            throw new SystemException("不能充值或提现0.00元!");
        } else {
            Double balance = balanceService.findBalanceByUserId(user.getId());
            if (balance + rechargeMoney < 0) {
                throw new SystemException("该用户没有足够的余额！");
            }
            content = String.format(UserMsgContent.CASHFORADMIN, user.getUsername(), user.getPhone(), user.getAlipayName(), user.getAlipayName(), dateFormat, df.format(Math.abs(rechargeMoney)));
        }
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
    public Boolean setComfirmById(Long rechargeId, Double percent) {
        DecimalFormat df = new DecimalFormat("######0.00");
        //查询出该条充值记录的信息
        Recharge rechargeFromMsg = selectOne(rechargeId);

        Double rechargeMoney = rechargeFromMsg.getRechargeMoney();
        String rechargeMoneyFormat = df.format(rechargeMoney);


        //查出父级
        User user = userService.selectOne(rechargeFromMsg.getUserId());
        Long parentId = user.getParentId();
        Date newDate = new Date();
        //如果存在父级联系人
        if (parentId != null && parentId != 0 && rechargeMoney > 0) {
            //佣金和积分
            double brokerage = rechargeMoney * percent;
            String brokerageFormat = df.format(brokerage);
            //更改佣金和积分以及记录
            balanceService.updateUserBalance(parentId, brokerage);
            integralService.updateUserIntegral(parentId, brokerage);

            BalanceRecord pbalanceRecord = new BalanceRecord(parentId, brokerage, newDate);
            balanceRecordService.save(pbalanceRecord);
            IntegralRecord pintegralRecord = new IntegralRecord(parentId, brokerage, newDate);
            integralRecordService.save(pintegralRecord);
            //发送消息

            UserMsg puserMsg = new UserMsg(parentId, null, null, false, newDate);
            String pcontent = String.format(UserMsgContent.BROKEREMSG, user.getAlipayName(), user.getPhone(), brokerageFormat, brokerageFormat);
            puserMsg.setContent(pcontent);
            userMsgService.save(puserMsg);
        }
        //更改用户余额
        balanceService.updateUserBalance(rechargeFromMsg.getUserId(), rechargeMoney);
        //增加余额记录
        BalanceRecord balanceRecord = new BalanceRecord(rechargeFromMsg.getUserId(), rechargeMoney, newDate);
        balanceRecordService.save(balanceRecord);
        //只有充值情况才有积分变动
        if (rechargeMoney > 0) {
            //更改用户积分
            integralService.updateUserIntegral(rechargeFromMsg.getUserId(), rechargeMoney);
            //增加积分记录
            IntegralRecord integralRecord = new IntegralRecord(rechargeFromMsg.getUserId(), rechargeMoney, newDate);
            integralRecordService.save(integralRecord);
        }

        //给充值用户发消息
        UserMsg userMsg = new UserMsg(rechargeFromMsg.getUserId(), null, null, false, newDate);
        String content;
        if (rechargeMoney > 0) {
            content = String.format(UserMsgContent.RECHARGEFORUSER, rechargeMoneyFormat);
        } else {
            content = String.format(UserMsgContent.CASHFFORUSER, df.format(Math.abs(rechargeMoney)));
        }

        userMsg.setContent(content);
        userMsgService.save(userMsg);

        rechargeFromMsg.setIsConfirm(true);
        return update(rechargeFromMsg);
    }
}
