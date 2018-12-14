package test;

import com.QAApplication;
import com.entity.Label;
import com.entity.Problem;
import com.entity.ProblemLabel;
import com.service.ProblemLabelService;
import com.service.ProblemService;
import com.web.BaseFeign;
import entity.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import util.IdUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QAApplication.class)
public class TestQA {

    @Autowired
    private BaseFeign baseFeign;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ProblemLabelService problemLabelService;

    private final String USERID = "1";
    private final String NICKNAME = "admin";

    @Test
    public void saveProblem() {
        Result result = baseFeign.findAll();
        List labels = (List) result.getData();
        Random random = new Random();
        //插入problem
        for (int i = 1; i <= 20; i++) {
            Long id = IdUtil.getId();
            Problem problem = new Problem(id+"", "titile" + i, "content?" + i, new Date(), null, USERID, NICKNAME, 0, 0, "no", "", null);
            problemService.save(problem);

            int index = random.nextInt(labels.size());
            Map<String,Object> map = (Map<String, Object>) labels.get(index);
            Long lid = IdUtil.getId();
            ProblemLabel problemLabel = new ProblemLabel(lid + "", problem.getId(), map.get("id").toString());
            problemLabelService.save(problemLabel);
        }
        System.out.println("finish....");
    }

    @Test
    public void testFeign() {
        Result result = baseFeign.findAll();
        System.out.println(result.getData());
    }
}
