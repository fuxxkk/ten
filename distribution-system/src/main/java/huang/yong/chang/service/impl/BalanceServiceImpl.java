package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.mapper.BalanceMapper;
import huang.yong.chang.service.BalanceService;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl extends BaseServiceImpl<Balance, BalanceMapper> implements BalanceService {
    @Override
    public Integer findBalanceByUserId(Long id) {
        QueryWrapper<Balance> balanceQueryWrapper = new QueryWrapper<>();
        balanceQueryWrapper.eq("user_id", id);
        return mapper.selectOne(balanceQueryWrapper).getBalance();
    }
}
