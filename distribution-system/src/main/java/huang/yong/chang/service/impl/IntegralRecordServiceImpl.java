package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.IntegralRecord;
import huang.yong.chang.entity.request.IntegralRecordPageRequest;
import huang.yong.chang.mapper.IntegralRecordMapper;
import huang.yong.chang.service.IntegralRecordService;
import huang.yong.chang.util.ContextUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegralRecordServiceImpl extends BaseServiceImpl<IntegralRecord, IntegralRecordMapper> implements IntegralRecordService {


    @Override
    public List<IntegralRecord> findPageByUserId(IntegralRecordPageRequest integralRecordPageRequest) {

        if (integralRecordPageRequest.getUserId()==null) {
            integralRecordPageRequest.setUserId(ContextUtils.getUser().getId());
        }
        integralRecordPageRequest.setPage(integralRecordPageRequest.getPage()-1);
        return mapper.findPageByUserId(integralRecordPageRequest);
    }
}
