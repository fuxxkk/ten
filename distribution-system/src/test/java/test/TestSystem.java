package test;

import huang.DistributionApplication;
import huang.yong.chang.entity.Recharge;
import huang.yong.chang.entity.User;
import huang.yong.chang.service.RechargeService;
import huang.yong.chang.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DistributionApplication.class)
public class TestSystem {

    @Autowired
    UserService userService;

    @Autowired
    private RechargeService rechargeService;

    @Test
    public void test() {
        List<User> users = userService.findAll();
        users.stream().forEach(user -> {
            System.out.println(user.getId());
        });
        System.out.println(users);
    }

    @Test
    public void recharge() {
        Recharge recharge = new Recharge();
        recharge.setUserId(527592008828211200L);
        for (int i = 20; i < 40; i++) {
            recharge.setRechargeMoney((double)i);
            rechargeService.rechage(recharge);
        }
    }

    @Test
    public void confirm() {
        long[] ls = {527999423742627840l};
        for (long s :
                ls) {
            rechargeService.setComfirmById(s,0.2);
            
        }
    }
}
