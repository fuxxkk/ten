package huang.yong.chang.service.impl;

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
    public List<BalanceRecord> findByUserId(BalanceRecordPageRequest balanceRecordPageRequest) {

        if (balanceRecordPageRequest.getUserId()==null) {
            balanceRecordPageRequest.setUserId(ContextUtils.getUser().getId());
        }
        balanceRecordPageRequest.setPage(balanceRecordPageRequest.getPage()-1);
        return mapper.findPageByUserId(balanceRecordPageRequest);
    }
}
