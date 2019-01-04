package huang.yong.chang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import huang.yong.chang.base.BaseService;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.request.RechargePageRequest;
import huang.yong.chang.excep.SystemException;

import java.util.List;

public interface RechargeService extends BaseService<Recharge> {

    /**
     * 用户充值
     * @param recharge
     * @return
     */
    Boolean rechage(Recharge recharge) throws SystemException;

    /**
     * 查询用户充值记录（分页）
     * @param rechargePageRequest
     * @return
     */
    IPage<Recharge> findByUserId(RechargePageRequest rechargePageRequest);

    /**
     * 设置成确定充值
     * @param rechargeId
     * @return
     */
    Boolean setComfirmById(Long rechargeId,Double percent,Double money);
}
