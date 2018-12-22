package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Recharge;

public interface RechargeService extends BaseService<Recharge> {

    /**
     * 用户充值
     * @param recharge
     * @return
     */
    Boolean rechage(Recharge recharge);
}
