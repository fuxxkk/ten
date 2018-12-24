package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.mapper.BalanceMapper;
import huang.yong.chang.service.BalanceService;
import huang.yong.chang.util.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BalanceServiceImpl extends BaseServiceImpl<Balance, BalanceMapper> implements BalanceService {
    @Override
    public Double findBalanceByUserId(Long id) {
        if (id==null) {
            id = ContextUtils.getUser().getId();
        }
        QueryWrapper<Balance> balanceQueryWrapper = new QueryWrapper<>();
        balanceQueryWrapper.eq("user_id", id);
        return mapper.selectOne(balanceQueryWrapper).getBalance();
    }

    @Override
    public Boolean updateUserBalance(Long userId, Double money) {
        QueryWrapper<Balance> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        Balance one = mapper.selectOne(wrapper);
        Balance balance = new Balance();
        balance.setBalance(one.getBalance() + money);
        balance.setModifyDate(new Date());
        return mapper.update(balance, wrapper) > 0 ? true : false;
    }
}
