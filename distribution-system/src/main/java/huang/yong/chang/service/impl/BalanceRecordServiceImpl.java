package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.BalanceRecord;
import huang.yong.chang.entity.request.BalanceRecordPageRequest;
import huang.yong.chang.mapper.BalanceRecordMapper;
import huang.yong.chang.service.BalanceRecordService;
import huang.yong.chang.util.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceRecordServiceImpl extends BaseServiceImpl<BalanceRecord, BalanceRecordMapper> implements BalanceRecordService {
    @Override
    public IPage<BalanceRecord> findByUserId(BalanceRecordPageRequest balanceRecordPageRequest) {

        if (balanceRecordPageRequest.getUserId() == null) {
            balanceRecordPageRequest.setUserId(ContextUtils.getUser().getId());
        }

        Page<BalanceRecord> balanceRecordPage = new Page<>(balanceRecordPageRequest.getPage(), balanceRecordPageRequest.getPageSize());
        QueryWrapper<BalanceRecord> balanceRecordQueryWrapper = new QueryWrapper<>();
        balanceRecordQueryWrapper.eq("user_id", balanceRecordPageRequest.getUserId());
        if (balanceRecordPageRequest.getAsc() != null && StringUtils.isNotEmpty(balanceRecordPageRequest.getOrderByColumn()) ) {
            balanceRecordQueryWrapper.orderBy(true, balanceRecordPageRequest.getAsc(), balanceRecordPageRequest.getOrderByColumn());
        }
        IPage<BalanceRecord> recordIPage = mapper.selectPage(balanceRecordPage, balanceRecordQueryWrapper);
        return recordIPage;
    }
}
