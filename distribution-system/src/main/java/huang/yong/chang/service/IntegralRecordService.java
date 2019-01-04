package huang.yong.chang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.IntegralRecord;
import huang.yong.chang.entity.request.IntegralRecordPageRequest;

import java.util.List;

public interface IntegralRecordService extends BaseService<IntegralRecord> {

    /**
     * 根据查询用户积分（分页）
     * @param integralRecordPageRequest
     * @return
     */
    IPage<IntegralRecord> findPageByUserId(IntegralRecordPageRequest integralRecordPageRequest);
}
