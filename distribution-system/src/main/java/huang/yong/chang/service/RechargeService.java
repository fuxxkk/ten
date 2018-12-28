package huang.yong.chang.service;

import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.request.RechargePageRequest;

import java.util.List;

public interface RechargeService extends BaseService<Recharge> {

    /**
     * 用户充值
     * @param recharge
     * @return
     */
    Boolean rechage(Recharge recharge);

    /**
     * 查询用户充值记录（分页）
     * @param rechargePageRequest
     * @return
     */
    List<Recharge> findByUserId(RechargePageRequest rechargePageRequest);

    /**
     * 设置成确定充值
     * @param id
     * @return
     */
    Boolean setComfirmById(Long id,Double percent);
}
