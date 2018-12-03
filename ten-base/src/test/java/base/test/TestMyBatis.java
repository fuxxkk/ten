package base.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import main.BaseApplication;
import main.entity.Label;
import main.mapper.LabelMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.IdUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class TestMyBatis {

    @Autowired
    private LabelMapper labelMapper;

    @Test
    public void insert() throws InterruptedException {
        Observable.range(1,1000).observeOn(Schedulers.single()).forEach(e->{
            Long id = IdUtil.getId();
            Label label = new Label(id+"","t"+e,"0",e+1,e+2,"0");
            label.insert();
        });
        Thread.sleep(5000L);
    }

    @Test
    public void select() {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.eq("id", "1");
        Label label = labelMapper.selectOne(wrapper);
        System.out.println(label);
    }
}
