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
        long[] ls = {527999423742627840l,
                527999432122847232l,
                527999439148306432l,
                527999446605778944l,
                527999453203419136l,
                527999460354707456l,
                527999467254337536l,
                527999473784868864l,
                527999481036820480l,
                527999488200691712l,
                527999495003852800l,
                527999502104809472l,
                527999509474201600l,
                527999516533215232l,
                527999523730640896l,
                527999530902900736l,
                527999537873833984l,
                527999544425336832l,
                527999551366909952l,
                527999557998104576l};
        for (long s :
                ls) {
            rechargeService.setComfirmById(s,0.2);
            
        }
    }
}
