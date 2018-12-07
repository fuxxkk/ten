package reruit.test;

import com.RecruitApplication;
import com.entity.Enterprise;
import com.entity.Recruit;
import com.service.EnterpriseService;
import com.service.RecruitService;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.IdUtil;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RecruitApplication.class)
public class TestEnterprise {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private RecruitService recruitService;

    @Test
    public void save() throws InterruptedException {
        Observable.range(1,120).observeOn(Schedulers.io()).forEach(e->{
            Random random = new Random();
            int isHot = random.nextInt(2);
            Enterprise enterprise = new Enterprise(IdUtil.getId()+"","e"+e,"summary"+e,"earth"+e,"",
                    String.valueOf(100+e)+","+String.valueOf(10+e),isHot+"","logo"+e,1000+e,"");
            enterpriseService.save(enterprise);
        });

        Thread.sleep(20000L);
    }

    @Test
    public void saveRecruit() throws InterruptedException {
        List<Enterprise> enterprises = enterpriseService.findAll();
        System.out.println(enterprises.size());

        Flowable.fromIterable(enterprises).observeOn(Schedulers.io()).forEach(enterprise -> {
            Random random = new Random();
            int i = random.nextInt(2) + 2;
            Flowable.range(1,i).observeOn(Schedulers.single()).forEach(e->{
                Recruit recruit = new Recruit(IdUtil.getId() + "", "job" + e, (1000 + e * 100) + "~" + (5000 + e * 200), "none", "none", "", "",
                        enterprise.getId(), new Date(), new Random().nextInt(4)+"", "", "", "", "");
                recruitService.save(recruit);
            });
        });

        Thread.sleep(30000L);
    }
}
