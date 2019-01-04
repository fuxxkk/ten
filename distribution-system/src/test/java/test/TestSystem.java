package test;

import huang.DistributionApplication;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.User;
import huang.yong.chang.excep.SystemException;
import huang.yong.chang.service.RechargeService;
import huang.yong.chang.service.UserItemService;
import huang.yong.chang.service.UserService;
import huang.yong.chang.service.impl.UserItemServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DistributionApplication.class)
public class TestSystem {

    @Autowired
    UserService userService;

    @Autowired
    private RechargeService rechargeService;

    @Autowired
    private UserItemService userItemService;

    @Test
    public void test() {
        List<User> users = userService.findAll();
        users.stream().forEach(user -> {
            System.out.println(user.getId());
        });
        System.out.println(users);
    }

    @Test
   //33333
    public void recharge() throws SystemException {
        Recharge recharge = new Recharge();
        recharge.setUserId(527592008828211200L);
        for (int i = 20; i < 40; i++) {
            recharge.setRechargeMoney((double)i);
            rechargeService.rechage(recharge);
        }
    }

    @Test
    public void confirm() {
        //11111111
        long[] ls = {527999423742627840l};
        for (long s :
                ls) {
            rechargeService.setComfirmById(s,0.2);
            
        }
    }

    @Test
    public void test1() {
        User user = userService.selectOne(528717591570173952L);
        List<Long> list = com.google.common.collect.Lists.newArrayList();
        userItemService.getUserLevelList(user,list);
        System.out.println(list);
    }
}
