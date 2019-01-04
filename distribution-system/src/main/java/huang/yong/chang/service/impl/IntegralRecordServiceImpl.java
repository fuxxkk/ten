package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.BalanceRecord;
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
    public IPage<IntegralRecord> findPageByUserId(IntegralRecordPageRequest integralRecordPageRequest) {

        if (integralRecordPageRequest.getUserId()==null) {
            integralRecordPageRequest.setUserId(ContextUtils.getUser().getId());
        }

        Page<IntegralRecord> integralRecordPage = new Page<>(integralRecordPageRequest.getPage(), integralRecordPageRequest.getPageSize());
        QueryWrapper<IntegralRecord> integralRecordQueryWrapper = new QueryWrapper<>();
        integralRecordQueryWrapper.eq("user_id", integralRecordPageRequest.getUserId());
        if (integralRecordPageRequest.getIsAsc() != null && StringUtils.isNotEmpty(integralRecordPageRequest.getOrderByColumn()) ) {
            integralRecordQueryWrapper.orderBy(true, integralRecordPageRequest.getIsAsc(), integralRecordPageRequest.getOrderByColumn());
        }
        IPage<IntegralRecord> recordIPage = mapper.selectPage(integralRecordPage, integralRecordQueryWrapper);
        return recordIPage;
    }
}
