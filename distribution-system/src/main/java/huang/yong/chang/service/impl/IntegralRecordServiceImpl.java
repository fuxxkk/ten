package huang.yong.chang.service.impl;

import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.IntegralRecord;
import huang.yong.chang.mapper.IntegralRecordMapper;
import huang.yong.chang.service.IntegralRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegralRecordServiceImpl extends BaseServiceImpl<IntegralRecord, IntegralRecordMapper> implements IntegralRecordService {
    @Override
    public List<IntegralRecord> findPageByUserId(Long userId) {
        return null;
    }
}
