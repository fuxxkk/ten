package huang.yong.chang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.UserMsg;
import huang.yong.chang.entity.request.RechargePageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RechargeMapper extends BaseMapper<Recharge> {

    List<Recharge> findByUserId(RechargePageRequest rechargePageRequest);
}
