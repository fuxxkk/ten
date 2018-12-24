package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.IntegralRecord;

import java.util.List;

public interface IntegralRecordService extends BaseService<IntegralRecord> {

    /**
     * 根据查询用户积分（分页）
     * @param userId
     * @return
     */
    List<IntegralRecord> findPageByUserId(Long userId);
}
