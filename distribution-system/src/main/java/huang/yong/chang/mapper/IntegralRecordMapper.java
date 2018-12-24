package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.IntegralRecord;
import huang.yong.chang.entity.request.IntegralRecordPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IntegralRecordMapper extends BaseMapper<IntegralRecord> {

    List<IntegralRecord> findPageByUserId(IntegralRecordPageRequest integralRecordPageRequest);
}
