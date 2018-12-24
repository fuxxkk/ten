package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.BalanceRecord;
import huang.yong.chang.entity.request.BalanceRecordPageRequest;

import java.util.List;

public interface BalanceRecordService extends BaseService<BalanceRecord> {

    /**
     * 根据用户查询余额变动记录
     * @param balanceRecordPageRequest
     * @return
     */
    List<BalanceRecord> findByUserId(BalanceRecordPageRequest balanceRecordPageRequest);
}
