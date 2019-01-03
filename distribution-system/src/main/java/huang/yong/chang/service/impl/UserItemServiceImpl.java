package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.Balance;
import huang.yong.chang.entity.DTO.UserItemDTO;
import huang.yong.chang.entity.Item;
import huang.yong.chang.entity.User;
import huang.yong.chang.entity.UserItem;
import huang.yong.chang.entity.request.UserItemPageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.mapper.UserItemMapper;
import huang.yong.chang.service.BalanceRecordService;
import huang.yong.chang.service.BalanceService;
import huang.yong.chang.service.ItemService;
import huang.yong.chang.service.UserItemService;
import huang.yong.chang.util.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserItemServiceImpl extends BaseServiceImpl<UserItem, UserItemMapper> implements UserItemService {

    @Autowired
    private BalanceService balanceService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BalanceRecordService balanceRecordService;

    @Override
    public Boolean buyItem(Long itemId) throws SystemException {
        User user = ContextUtils.getUser();
        Optional.ofNullable(user).orElseThrow(() -> new SystemException("请登录够再购买"));

        //查询余额
        Double balance = balanceService.findBalanceByUserId(user.getId());
        Item item = itemService.selectOne(itemId);
        if (balance-item.getPrice()<0) {
            throw new SystemException("您没有足够的余额购买！");
        }

        //查询当前用户购买该商品的次数
        QueryWrapper<UserItem> userItemQueryWrapper = new QueryWrapper<>();
        userItemQueryWrapper.eq("user_id", user.getId()).eq("item_id", itemId);
        Integer count = mapper.selectCount(userItemQueryWrapper);
        if (count>=2) {
            throw new SystemException("您不能购买该商品超过2次！");
        }

        //查询用户购买的商品是否在生长周期内
        userItemQueryWrapper.eq("status", 0);
        Integer statusCount = mapper.selectCount(userItemQueryWrapper);
        if (statusCount>0) {
            throw new SystemException("您已存在同样的商品在生长周期内，请待商品生长完再购买！");
        }

        //购买商品，扣减余额，增加商品购买记录
        balanceService.updateUserBalance(user.getId(), -item.getPrice());

        UserItem userItem = new UserItem(user.getId(), itemId, new Date());
        save(userItem);

        //todo 邀请返佣
            //判断是否新用户
        if (user.getIsNew()) {

        }


        return true;
    }

    @Override
    public List<UserItemDTO> findPage(UserItemPageRequest userItemPageRequest) {
        return null;
    }

    //todo 购买返佣


    //邀请返佣
    private void inviteReturn(User user) {

    }


}
