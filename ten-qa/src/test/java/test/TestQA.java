package test;

import com.QAApplication;
import com.entity.Problem;
import com.web.BaseFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.IdUtil;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QAApplication.class)
public class TestQA {

    @Autowired
    private BaseFeign baseFeign;

    private final String USERID = "1";
    private final String NICKNAME = "admin";
    @Test
    public void saveProblem() {
        //插入problem
        for (int i = 1; i <= 20; i++) {
            Long id = IdUtil.getId();
            Problem problem = new Problem(id+"", "titile" + i, "content?" + i, new Date(), null, USERID, NICKNAME, 0, 0, "no", "", null);

        }
    }

    @Test
    public void testFeign() {
        baseFeign.findAll();
    }
}
