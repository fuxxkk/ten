package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.BalanceRecord;
import huang.yong.chang.entity.request.BalanceRecordPageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BalanceRecordMapper extends BaseMapper<BalanceRecord> {
    List<BalanceRecord> findPageByUserId(BalanceRecordPageRequest balanceRecordPageRequest);
}
