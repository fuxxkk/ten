package test;

import huang.DistributionApplication;
import huang.yong.chang.entity.User;
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

    @Test
    public void test() {
        List<User> users = userService.findAll();
        users.stream().forEach(user -> {
            System.out.println(user.getId());
        });
        System.out.println(users);
    }
}
