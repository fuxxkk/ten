package huang.yong.chang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import huang.yong.chang.base.BaseService;
import huang.yong.chang.base.PageRequest;
import huang.yong.chang.entity.Integral;

import java.util.List;

public interface IntegralService extends BaseService<Integral> {

    /**
     * 更改用户积分
     * @param userId
     * @param integral
     * @return
     */
    Boolean updateUserIntegral(Long userId, Double integral);

    /**
     * 查询积分记录(分页查询）
     * @param pageRequest
     * @return
     */
    IPage<Integral> findPage(PageRequest pageRequest);

    /**
     * 查询当前用户积分
     * @param userId
     * @return
     */
    Double findByUserId(Long userId);
}
