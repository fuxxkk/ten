package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Balance;

public interface BalanceService extends BaseService<Balance> {

    /**
     * 查询当前用户余额
     * @param id
     * @return
     */
    Double findBalanceByUserId(Long id);
}
