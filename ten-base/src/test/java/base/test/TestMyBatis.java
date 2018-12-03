package base.test;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import main.BaseApplication;
import main.entity.Label;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class TestMyBatis {


    @Test
    public void insert() {
        Observable.range(1,10).observeOn(Schedulers.single()).forEach(e->{
            //
            Label label = new Label(e+"","t"+e,"0",e,e,"0");

        });
    }

}
