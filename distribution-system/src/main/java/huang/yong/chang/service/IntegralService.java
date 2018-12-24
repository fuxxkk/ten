package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Integral;

public interface IntegralService extends BaseService<Integral> {

    /**
     * 更改用户积分
     * @param userId
     * @param integral
     * @return
     */
    Boolean updateUserIntegral(Long userId, Double integral);

}
