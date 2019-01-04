package huang.yong.chang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import huang.yong.chang.base.BaseServiceImpl;
import huang.yong.chang.entity.*;
import huang.yong.chang.entity.DTO.UserItemDTO;
import huang.yong.chang.entity.request.UserItemPageRequest;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.mapper.UserItemMapper;
import huang.yong.chang.service.*;
import huang.yong.chang.util.ContextUtils;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private UserService userService;

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
        Date newDate = new Date();
        BalanceRecord buyRecord = new BalanceRecord(user.getId(), -item.getPrice(), newDate, "购买商品：" + item.getName());
        balanceRecordService.save(buyRecord);

        UserItem userItem = new UserItem(user.getId(), itemId, newDate);
        save(userItem);

        //todo 邀请返佣
            //判断是否新用户
        if (user.getIsNew()) {
            List<Long> list = Lists.newArrayList();
            getUserLevelList(user,list);

            for (int i = 0; i < list.size(); i++) {
                double money;
                switch (i){
                    case 0: //A级返佣
                        money = 10;
                        break;
                    case 1:  //B级返佣
                        money=5;
                        break;
                    case 2:  //C级返佣
                        money=3;
                        break;
                    default:  //D级以上返佣
                        money=1;
                        break;
                }
                Long parentId = list.get(i);
                //余额记录
                String source = "%s 购买 - %d级推荐佣金";
                BalanceRecord balanceRecord =
                        new BalanceRecord(parentId, money, newDate, String.format(source, user.getPhone(), i + 1));
                balanceRecordService.save(balanceRecord);
                //增加余额
                balanceService.updateUserBalance(parentId, money);
            }

            user.setIsNew(false);
            userService.updateUser(user);
        }


        return true;
    }

    @Override
    public List<UserItemDTO> findPage(UserItemPageRequest userItemPageRequest) throws SystemException {
        User user = ContextUtils.getUser();
        Optional.ofNullable(user).orElseThrow(() -> new SystemException("请登录够再操作"));
        userItemPageRequest.setUserId(user.getId());
        userItemPageRequest.setPage(userItemPageRequest.getPage()-1);
        List<UserItem> pageList = mapper.findPage(userItemPageRequest);
        Iterable<UserItemDTO> userItemDTOS = Observable.fromIterable(pageList).observeOn(Schedulers.io()).map(x -> {
            Item item = itemService.selectOne(x.getItemId());
            UserItemDTO userItemDTO = new UserItemDTO();
            userItemDTO.setBuyDate(x.getCreateDate());
            userItemDTO.setItem(item);
            userItemDTO.setStatus(x.getStatus());
            Integer m = item.getCycle();
            Integer s = x.getCurrentDay();
            userItemDTO.setProgress(s + "/" + m);
            return userItemDTO;
        }).blockingIterable();
        return Lists.newArrayList(userItemDTOS);
    }

    //todo 购买返佣


    //用户等级集合
    public void getUserLevelList(User user,List<Long> list) {
        Long parentId = user.getParentId();
        if (parentId != null && parentId != 0) {
            list.add(parentId);
            User parent = userService.selectOne(parentId);
            getUserLevelList(parent,list);
        }
    }


}
