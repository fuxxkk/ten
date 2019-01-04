package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.base.PageRequest;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.entity.Integral;
import huang.yong.chang.mapper.BalanceMapper;
import huang.yong.chang.mapper.IntegralMapper;
import huang.yong.chang.service.BalanceService;
import huang.yong.chang.service.IntegralService;
import huang.yong.chang.util.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegralServiceImpl extends BaseServiceImpl<Integral, IntegralMapper> implements IntegralService {
    @Override
    public Boolean updateUserIntegral(Long userId, Double integral) {
        QueryWrapper<Integral> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        Integral selectOne = mapper.selectOne(wrapper);
        selectOne.setIntegral(selectOne.getIntegral()+integral);
        selectOne.setModifyDate(new Date());
        return mapper.update(selectOne, wrapper) > 0 ? true : false;
    }

    @Override
    public List<Integral> findPage(PageRequest pageRequest) {
        return mapper.findPage(pageRequest);
    }

    @Override
    public Double findByUserId(Long userId) {
        if (userId == null || userId == 0) {
            userId = ContextUtils.getUser().getId();
        }
        QueryWrapper<Integral> integralQueryWrapper = new QueryWrapper<>();
        integralQueryWrapper.eq("user_id", userId);

        return mapper.selectOne(integralQueryWrapper).getIntegral();
    }
}
